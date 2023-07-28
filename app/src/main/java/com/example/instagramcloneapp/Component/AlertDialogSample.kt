package com.example.instagramcloneapp.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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