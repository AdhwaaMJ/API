package com.project.api.presentation.screens.popular

import android.graphics.Movie
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.project.api.Constant.MOVIE_IMAGE_BASE_URL
import com.project.api.R
import com.project.api.model.BackdropSize
import com.project.api.model.Results
import com.project.api.presentation.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.http.Query


@Composable
fun MainScreen(
    navController : NavHostController,
    popularMoviesState: MutableStateFlow<PagingData<Results>>
) {
    val moviePagingItems = popularMoviesState.collectAsLazyPagingItems()
    Box {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center) {
            items(moviePagingItems.itemCount) {index ->
                if (moviePagingItems[index]?.adult==false){
                    AsyncImage(model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${moviePagingItems[index]?.posterPath}",
                        contentDescription = "",
                        modifier = Modifier
                            .padding(2.dp)
                            .clickable {
                                navController.navigate(AppScreen.MoviesDetailsScreen.rout + "/${moviePagingItems[index]}")
                            },
                        contentScale = ContentScale.FillWidth,
                        error = painterResource(id = R.drawable.ic_launcher_foreground),
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
                        )
                   }

               }

           }
        moviePagingItems.apply {
            when{
                loadState.refresh is LoadState.Loading ->{
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error ->{
                    val error = moviePagingItems.loadState.append as LoadState.Error
                    Text(
                        text = error.error.localizedMessage.orEmpty(),
                        modifier = Modifier,
                        )

                }


            }


        }
    }
}


