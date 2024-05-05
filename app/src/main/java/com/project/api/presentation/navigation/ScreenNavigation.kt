package com.project.api.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.api.presentation.screens.onBoarding.OnBoardingScreen
import com.project.api.presentation.screens.onBoarding.OnBoardingViewModel
import com.project.api.presentation.screens.popular.MainScreen
import com.project.api.presentation.screens.popular.PopularMoviesViewModel

sealed class AppScreen(val rout: String) {
    object endOfONboardingScreen : AppScreen("EndOfOnBoarding")
    object MainScreen : AppScreen("mainScreen")
    object OnboardingScreen:AppScreen("OnBoarding")

    object Home : AppScreen("popular_movie_screen")

    object Search : AppScreen ("search_route")
    object Profile : AppScreen ("profile_route")
}

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem>{
        return  listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Search,
                route = AppScreen.Home.rout
            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = AppScreen.Search.rout

            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = AppScreen.Profile.rout

            )


        )
    }
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
){
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = onBoardingViewModel.startDestination
    ){
        composable(AppScreen.OnboardingScreen.rout){
            OnBoardingScreen(onBoardingViewModel,navController)
        }

        composable(AppScreen.MainScreen.rout){
            val viewModel = hiltViewModel<PopularMoviesViewModel>()
            MainScreen(navController,viewModel.popularMoviesState)

        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController){
    popUpTo(navController.currentBackStackEntry?.destination?.route?: return){
        inclusive =true
    }
}




//fun NavGraph(navController: NavHostController = rememberNavController()) {
//
//    NavHost(navController = navController, startDestination = Screen.OnboardingScreen.rout) {
//        composable(Screen.OnboardingScreen.rout){
//            OnBoardingScreen(navController)
//        }
//        composable(Screen.endOfONboardingScreen.rout) {
//            ScreenThree(navController)
//        }
//        composable(Screen.mainScreen.rout) {
//            MainScreen()
//        }
//
//    }
//
//}

