package com.example.instagramcloneapp.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramcloneapp.Component.AlertDialog
import com.example.instagramcloneapp.Routes


@Composable
fun SignUp(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        SignUpContent(navController = navController)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(navController: NavHostController){
Column(
modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 200.dp),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {

    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val password2 = remember { mutableStateOf(TextFieldValue()) }


    Text(text = "Sign Up", style = TextStyle(fontSize = 40.sp))

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        label = { Text(text = "Username") },
        value = username.value,
        onValueChange = { username.value = it })

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        label = { Text(text = "Password") },
        value = password.value,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { password.value = it })

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        label = { Text(text = "Password Again") },
        value = password2.value,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { password2.value = it })


    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {

                /*if (password.value.text == password2.value.text){
                navController.navigate(Routes.Login.route) }*/
                navController.navigate(Routes.AlertDialogSample.route)
                     },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)

        ){
            Text(text = "Save")
        }
    }
}
}


