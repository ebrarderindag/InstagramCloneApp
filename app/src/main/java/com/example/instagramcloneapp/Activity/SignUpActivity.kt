package com.example.instagramcloneapp.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramcloneapp.Component.AlertDialog
import com.example.instagramcloneapp.data.Information
import com.example.instagramcloneapp.data.PostItem
import com.example.instagramcloneapp.data.Users
import com.example.instagramcloneapp.Extensions.getParcelableArrayCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataUsers = intent?.extras?.getParcelableArrayCompat("data", Users::class.java)
        println("Data: " + dataUsers)

        setContent {
            SignUpContent(dataUsers)
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(dataUsers: ArrayList<Users>?) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val password2 = remember { mutableStateOf(TextFieldValue()) }
        var isClicked by remember { mutableStateOf(false) }
        val context = LocalContext.current

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
                    isClicked = signUpControl(
                        username.value.text,
                        password.value.text,
                        password2.value.text,
                        dataUsers,
                        context
                    )
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Save")
            }
            if (isClicked) {
                AlertDialog()
            }
        }
    }
}

fun signUpControl (
    username: String,
    password: String,
    password2: String,
    dataUsers: ArrayList<Users>?,
    context: Context
):Boolean {


    var control =false
    if (username.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
        if (password.equals(password2)) {
            for (data in dataUsers!!) {
                if (!username.equals(data.information?.userName)) {
                   control = writeData(username, password)

                }else {
                    Toast.makeText(context, "Bu kullanıcı adı kullanılamıyor.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Girdiğiniz şifreler aynı değil.", Toast.LENGTH_SHORT).show()
        }

    } else {
        Toast.makeText(context, "Lutfen bilgilerinizi giriniz.", Toast.LENGTH_SHORT).show()
    }
    return control
}

fun writeData(username: String, password: String, ) : Boolean {
    lateinit var database : DatabaseReference
    database = FirebaseDatabase.getInstance().getReference("Users")

    val info = Information(id = 2, password = password, userName = username)
    val postList = ArrayList<PostItem>()
    postList.add(PostItem(id = 0, description = "", url = ""))

    val user = Users(info,postList)
    database.child("1").setValue(user).addOnSuccessListener(){

    }
    return true
}
