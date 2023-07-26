package com.example.instagramcloneapp.Component

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.instagramcloneapp.Activity.LoginActivity
import com.example.instagramcloneapp.Activity.SignUpActivity


@SuppressLint("UnrememberedMutableState")
@Composable
fun AlertDialog() {
    val activity = (LocalContext.current as? SignUpActivity)

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
                                activity?.finish()
                            })
                        {
                            Text("OK")
                        }
                    })
        }
    }
}