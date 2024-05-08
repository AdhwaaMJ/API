package com.project.api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.project.api.presentation.navigation.AppScreen
import com.project.api.presentation.navigation.BottomNavigationItem
import com.project.api.presentation.navigation.NavGraph
import com.project.api.presentation.navigation.popUpToTop
import com.project.api.ui.theme.APITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APITheme {
                val navController  = rememberNavController()

                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when(navBackStackEntry?.destination?.route){
                    AppScreen.OnboardingScreen.rout -> false
                    else -> true
                }

                val navigationSelectedItem = rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar){
                            NavigationBar {
                                BottomNavigationBar(navigationSelectedItem , navController)
                            }

                        }
                    }
                ) {paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavGraph(navController)
                    }

                }

            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationBar(
    navigationSelectedItem: MutableIntState,
    navController: NavHostController
){
    BottomNavigationItem().bottomNavigationItems()
        .forEachIndexed { index, navigationItem ->

            NavigationBarItem(
                selected = index == navigationSelectedItem.intValue,
                label = {
                    Text(navigationItem.label)
                },
                icon = {
                       Icon(navigationItem.icon,
                           contentDescription = navigationItem.label )
                       },
                onClick = {
                          navigationSelectedItem.intValue = index
                    navController.navigate(navigationItem.route){
                        popUpToTop(navController)
                        restoreState = true
                        launchSingleTop = true

                     }
                          },


                )
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

//@Composable
//fun MainScreen(
//    navController: NavController,
//    popularMoviesState: MutableState<UIState<SearchResponse>>
//){
//    val moviePagingItems = popularMoniesState.collectAsLazyPagingItems()
//    Box {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalArrangement = Arrangement.Center) {
//            items(moviePagingItems.itemCount) {index ->
//                if (moviePagingItems[index]?.adult==false){
//                    AsyncImage(model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${moviePagingItems[index]?.posterPath}",
//                        contentDescription = "",
//                        modifier = Modifier
//                            .padding(2.dp),
//                        contentScale = ContentScale.FillWidth,
//                        error = painterResource(id = R.drawable.),
//                        placeholder = painterResource(id = )
//                    )
//                }
//
//            }
//
//        }
//        moviePagingItems.apply {
//            when{
//                loadState.refresh is LoadState.Loading ->{
//                    Row(
//                        Modifier.fillMaxSize(),
//                        horizontalArrangement = Arrangement.CenterVertically
//                    ){
//                        CircularProgressIndicator()
//                    }
//                }
//
//                loadState.append is LoadState.Error ->{
//                    val error = moviePagingItems.loadState.append as LoadState.Error
//                    Text(
//                        text = error.error.localizedMessage.orEmpty(),
//                        modifier = Modifier,
//                    )
//                }
//
//            }
//        }
//    }
//
//}
//
//
