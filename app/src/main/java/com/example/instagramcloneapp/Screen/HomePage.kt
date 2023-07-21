package com.example.instagramcloneapp.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.data.User
import com.example.instagramcloneapp.Activity.HomeActivity
import com.example.instagramcloneapp.Activity.OptionMenu
import com.example.instagramcloneapp.data.DataProvider

@Composable
fun HomePage(navController: NavHostController) {
        OptionMenu(navController = navController)

        HomeContent(navController = navController)

}

@Composable
fun HomeContent(navController: NavHostController){
    val users = remember { DataProvider.userList}

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        items(
            items = users,
            itemContent = {
                UserListItem(user = it, navController = navController)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionMenu(navController: NavHostController){
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
                    onClick = { Toast.makeText(context, "Add Post", Toast.LENGTH_SHORT).show() }
                )
                DropdownMenuItem(
                    text = { Text("Save") },
                    onClick = { Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show() }
                )

            }

        }
    )
}


