package com.example.instagramcloneapp

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUp: Routes("SignUp")
    object ForgotPassword: Routes("ForgotPassword")
    object HomePage : Routes("HomePage")
    object ImageDetail : Routes("ImageDetail")
    companion object
}