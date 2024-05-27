package com.project.api.presentation.screens.Details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.api.Constant
import com.project.api.R
import com.project.api.model.BackdropSize
import com.project.api.model.UIState

@Composable
fun MoviesDetailsScreen(int: Int?, viewModel: MoviesDetailsViewModel) {
    LaunchedEffect(Unit) {
        if (int != null) {
            viewModel.getMoviesDetails(int)
        }
    }
    val result = viewModel.movieDetailState.collectAsState()

    when (val data = result.value) {
        is UIState.Empty -> {}
        is UIState.Error -> {}
        is UIState.Loading -> {}
        is UIState.Success -> {
            LazyColumn {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = "${Constant.MOVIE_IMAGE_BASE_URL}${BackdropSize.w1280}/${data.data?.backdrop_path}",
                            contentDescription = "",
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .heightIn(max = 300.dp),
                            contentScale = ContentScale.FillWidth,
                            error = painterResource(R.drawable.ic_launcher_foreground),
                            placeholder = painterResource(R.drawable.ic_launcher_foreground)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomEnd)
                                .padding(
                                    top = 120.dp,
                                    start = 12.dp,
                                    end = 12.dp
                                ) // Adjust padding as needed
                        ) {
                            Row(verticalAlignment = Alignment.Bottom) {
                                AsyncImage(
                                    model = "${Constant.MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${data.data?.poster_path}",
                                    contentDescription = "",
                                    modifier = Modifier,
                                    contentScale = ContentScale.FillWidth,
                                    error = painterResource(R.drawable.ic_launcher_foreground),
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground)
                                )

                                    Text(
                                        text = data.data?.title.orEmpty(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.White,
                                    )

                            }
                        }

                    }
                    }
                item {
                    Text(
                        text = data.data?.title.orEmpty(),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp)
                    )
                    Text(
                        text = data.data?.overview.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                }
            }
       }
   }
}



