package com.example.instagramcloneapp.Screen


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramcloneapp.Activity.HomeActivity
import com.example.instagramcloneapp.Activity.OptionMenu
import com.example.instagramcloneapp.Component.AlertDialog

import com.example.instagramcloneapp.Routes

@Composable
fun ScreenMain(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }
        composable(Routes.AlertDialogSample.route) {
            AlertDialog(navController = navController)
        }

        composable(Routes.SignUp.route) {
            SignUp(navController = navController)
        }

        composable(Routes.HomePage.route) {
            HomePage( navController = navController)
        }
        composable(Routes.ImageDetail.route) {
            ImageDetail( navController = navController)
        }

    }
}




