package com.example.instagramcloneapp

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUp: Routes("SignUp")
    object HomePage : Routes("HomePage")
    object ImageDetail : Routes("ImageDetail")
    object AlertDialogSample : Routes("AlertDialogSample")
    object HomeActivity : Routes("HomeActivity")

    companion object
}