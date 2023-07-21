package com.example.instagramcloneapp.Activity


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.instagramcloneapp.Screen.HomeContent


class HomeActivity() : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContent {
           OptionMenu()
       }}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionMenu(){
    val navController = rememberNavController()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Instagram")},
        actions =
        {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Load") },
                    onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
                )
                DropdownMenuItem(
                    text = { Text("Save") },
                    onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
                )

            }

        }
    )
}

