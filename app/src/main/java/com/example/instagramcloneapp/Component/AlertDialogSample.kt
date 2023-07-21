package com.example.instagramcloneapp.Component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

import com.example.instagramcloneapp.Routes
import com.example.instagramcloneapp.Screen.SignUpContent


@Composable
fun SignUp(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        AlertDialog(navController = navController)
    }
}
@Composable
fun AlertDialog(navController: NavHostController) {
    MaterialTheme {
        Column {

                AlertDialog(
                    onDismissRequest = {

                    },
                    title = {
                        Text(text = "Registration Successful")
                    },
                    text = {
                        Text("Redirected to Login")
                    },

                    confirmButton = {
                        Button(
                            onClick ={
                                navController.navigate("Login"){
                                    popUpTo(navController.graph.id){
                                        inclusive = true
                                    }
                                }
                            })
                        {
                            Text("OK")
                        }
                    })
        }
    }
}