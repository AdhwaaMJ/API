package com.project.api.presentation.screens.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.api.R
import com.project.api.presentation.navigation.AppScreen
import com.project.api.presentation.navigation.popUpToTop

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    val onBoardingCombleted by onBoardingViewModel.onBoardingCompleted.collectAsState()

    if (onBoardingCombleted){
        navController.navigate(AppScreen.MainScreen.rout){
            popUpToTop(navController)
        }
    }else {


        val pagerState = rememberPagerState { 3 }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSize = PageSize.Fill,
        ) {
            when (pagerState.currentPage) {
                0 -> ScreenOne()
                1 -> ScreenTwo()
                2 -> ScreenThree(navController){
                    onBoardingViewModel.saveOnBoardingState(true)
                }



            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .size(12.dp)
                        .background(color)
                )

            }
        }
    }




}


@Composable
fun ScreenOne(){
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.spacedBy(20.dp)


    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(20.dp)
            .paint(
                painterResource(id = R.drawable.movie_night_amico),
                contentScale = ContentScale.FillBounds,
                sizeToIntrinsics = false
            )
        )

        Text(text = "Welcome to CineSpectra!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)



        Text(text =
        "Explore the latest movies, reserve the perfect seats, " +
                "and experience the cinema in a whole new way.",
        fontSize= 15.sp, textAlign = TextAlign.Center)



        Box(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .rotate(-90.0f)
            .align(Alignment.End)
            .paint(
                painterResource(id = R.drawable.brutalist_10)
            )
            )
    }

}


@Composable
fun ScreenTwo(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.spacedBy(20.dp)


    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(20.dp)
            .paint(
                painterResource(id = R.drawable.home_cinema_amico),
                contentScale = ContentScale.FillBounds,
                sizeToIntrinsics = false
            )
        )

        Text(text = "Quick and Easy Booking",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)



        Text(text =
        "Reserve your favorite seat in just a few steps. No waiting, no hassle!",
            fontSize= 15.sp, textAlign = TextAlign.Center)



        Box(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .rotate(-90.0f)
            .align(Alignment.End)
            .paint(
                painterResource(id = R.drawable.brutalist_10)
            )
        )
    }


}

@Composable
fun ScreenThree(navController: NavHostController, finshedonboarding: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.spacedBy(20.dp)


    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(20.dp)
            .paint(
                painterResource(id = R.drawable.horror_movie_amico),
                contentScale = ContentScale.FillBounds,
                sizeToIntrinsics = false
            )
        )
        Text(text = "Tailored Just for You",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)

        Text(text =
        "Personalize your experience! With movie recommendations and exclusive offers, enjoy the cinema your way.",
            fontSize= 15.sp, textAlign = TextAlign.Center)


        Row {
            Box {

                Image(
                    painter = painterResource(id = R.drawable.brutalist_10),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(-90f)
                        .fillMaxSize()

                )
                Button(
                    onClick = { navController.navigate(AppScreen.MainScreen.rout)
                    {
                        popUpToTop(navController)
                    }
                    finshedonboarding.invoke()}) {
                    Text(text = "lets get started")
                }

            }
        }

    }
    }









//@Composable
//@Preview(showBackground = true)
//fun ScreenOnePreview(){
//    ScreenTwo()
//
//
//}
