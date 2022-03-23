package com.example.stylism_fashion_ecommerce

sealed class Screens(val route: String) {
    object FirstBoardingScreen : Screens("first_boarding_screen")
    object SecondBoardingScreen : Screens("second_boarding_screen")
    object ThirdBoardingScreen : Screens("third_boarding_screen")
    object SignInMethodsScreen : Screens("sign_in_methods_screen")
    object SignInWithEmailAndPassScreen : Screens("sign_in_with_email_and_pass_screen")
    object SignUpWithEmailAndPassScreen : Screens("sign_up_with_email_and_pass_screen")
    object ResetPasswordScreen : Screens("reset_password_screen")
    object CheckYourEmailScreen : Screens("check_your_email_screen")
    object SendOTPScreen: Screens("send_otp_screen")
    object EnterOTPScreen : Screens("enter_otp_screen")
}
