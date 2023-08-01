package com.example.instagramcloneapp.Activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramcloneapp.Data.User
import com.example.instagramcloneapp.Data.UserList
import com.example.instagramcloneapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.values
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {
    val firebaseDataBase = FirebaseDatabase.getInstance()
    val databaseRef = firebaseDataBase.getReference("UserList")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        setContent() {
            //val user = User(User.Information(1, "1", "e"))
            readData()
            LoginPage()


        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun LoginPage() {

        val activity = (LocalContext.current as? LoginActivity)
        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }
            val context = LocalContext.current


            Text(text = "Login", style = TextStyle(fontSize = 40.sp))

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
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {

                        if (username.value.text.isNotEmpty() && password.value.text.isNotEmpty()) {

                            //readData(username.value.text, password.value.text)

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            //startActivity(intent)
                            //activity?.finish()
                        } else {
                            Toast.makeText(
                                context,
                                "Kullanıcı adı veya şifre yanlış",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                        startActivity(intent)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    }


    private fun readData() {


        val dat = databaseRef.child("User1/Information")
        dat.child("Username").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user1 = dataSnapshot.getValue(String::class.java)
                println("Database::::$user1")
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                println("--------------------")
            }

        })



        /*println("Database: " + database.child( username).child("Information").child("Password"))
database.child( username).child("Information").child("Password").get().addOnSuccessListener {

    val password_data = it.value

    println("password data = $password_data")


}.addOnFailureListener{
    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
}*/
    }
}
