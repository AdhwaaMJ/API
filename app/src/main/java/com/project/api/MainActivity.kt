package com.project.api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.project.api.Constant.MOVIE_IMAGE_BASE_URL
import com.project.api.model.BackdropSize
import com.project.api.model.SearchResponse
import com.project.api.model.UIState
import com.project.api.presentation.navigation.NavGraph
import com.project.api.ui.theme.APITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            APITheme {
                NavGraph()
//                val viewModel by viewModels<PopularMoviesViewModel> ()
//                when(val result = viewModel.popularMoviesState.value){
//                    is UIState.Success -> {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(MaterialTheme.colorScheme.primaryContainer)
//                        ) {
//                            Box(modifier = Modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight(0.4f)
//                                .background(MaterialTheme.colorScheme.primaryContainer)
//                                .paint(
//                                    painterResource(id = R.drawable.background),
//                                    contentScale = ContentScale.FillBounds,
//                                    sizeToIntrinsics = false
//                                )
//                            )
//                            LazyColumn(
//                                modifier = Modifier.fillMaxSize()
//                            ) {
//                                item {
//////                                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
//////                                        contentDescription = "fil image"
////                                    )
//                                }
//                                items(result.data?.results.orEmpty()){
//                                    GlideImage(model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${it.posterPath}",
//                                        contentDescription = "",
//                                        modifier = Modifier
//                                            .padding(12.dp)
//                                    )
//                                    Text(
//                                        text =it.title.orEmpty(),
//                                        modifier = Modifier.padding(bottom = 8.dp)
//                                    )
//                                }
//
//                            }
//
////                        }
//
//
//                    }
//
//                    is UIState.Empty -> {}
//                    is UIState.Error -> {}
//                    is UIState.Loading -> {}
//                }


//                when(val result = viewModel.popularMoviesState.value){
//                    is UIState.Success -> {
//                        LazyColumn(modifier = Modifier
//                            .fillMaxSize()
//                            .background(MaterialTheme.colorScheme.primary)) {
//                            items(result.data?.results.orEmpty()) {
//                                Text(
//                                    text =it.title.orEmpty(),
//                                    modifier = Modifier.padding(12.dp))
//                            }
//
//                        }
//                    }
//                    is UIState.Empty -> {}
//                    is UIState.Error -> {}
//                    is UIState.Loading -> {}
                }
            }





            }
        }




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APITheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    popularMoviesState: MutableState<UIState<SearchResponse>>
){
    when(val result = popularMoviesState.value){
        is UIState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .paint(
                        painterResource(id = R.drawable.background),
                        contentScale = ContentScale.FillBounds,
                        sizeToIntrinsics = false
                    )
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
////                                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
////                                        contentDescription = "fil image"
//                                    )
                    }
                    items(result.data?.results.orEmpty()){
                        GlideImage(model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${it.posterPath}",
                            contentDescription = "",
                            modifier = Modifier
                                .padding(12.dp)
                        )
                        Text(
                            text =it.title.orEmpty(),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                }

            }


        }

        is UIState.Empty -> {}
        is UIState.Error -> {}
        is UIState.Loading -> {}
    }

}


