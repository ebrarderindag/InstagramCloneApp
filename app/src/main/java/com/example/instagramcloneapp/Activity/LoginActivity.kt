package com.example.instagramcloneapp.Activity

import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramcloneapp.data.DataModel
import com.example.instagramcloneapp.data.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LoginActivity : ComponentActivity() {
    private lateinit var database: DatabaseReference
    var data: DataModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            readData()
            LoginPageContent()


        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginPageContent() {
        (LocalContext.current as? LoginActivity)
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
                        loginControl(username.value.text, password.value.text, context)
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
                        val bundle= Bundle()
                        val dataList: ArrayList<Users>? =  data?.Users
                        bundle.putParcelableArrayList("data", dataList)
                        intent.putExtras(bundle)
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

    @Composable
     fun readData() {
        database = Firebase.database.getReference("")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(DataModel::class.java)?.let {
                    data = it
                } ?: run {
                    Toast.makeText(this@LoginActivity, "Veri bulunamadı", Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    fun loginControl(username: String, password: String, context: Context) {
        var i = 0
        if (username.isNotEmpty() && password.isNotEmpty()) {
            for (user in data?.Users!!) {
                i++
                if (user.information?.userName.equals(username)) {
                    if (user.information?.password.equals(password)) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        val bundle= Bundle()
                        bundle.putParcelable("user" , user)
                        intent.putExtras(bundle)
                        startActivity(intent)
                        this.finishAffinity()
                    } else {
                        Toast.makeText(context, "Sifre yanlis", Toast.LENGTH_SHORT).show()
                    }
                } else if (data!!.Users?.size == i ) {
                    Toast.makeText(context, "Kullanici bulunamadi", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Lutfen bilgilerinizi giriniz.", Toast.LENGTH_SHORT).show()
        }
    }
}
