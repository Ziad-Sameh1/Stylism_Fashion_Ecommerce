package com.example.stylism_fashion_ecommerce

sealed class Screens(val route: String) {
    object FirstBoardingScreen : Screens("first_boarding_screen")
    object SecondBoardingScreen : Screens("second_boarding_screen")
    object ThirdBoardingScreen : Screens("third_boarding_screen")
}
