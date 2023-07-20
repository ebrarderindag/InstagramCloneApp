package com.example.instagramcloneapp.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instagramclone.data.User
import com.example.instagramcloneapp.data.DataProvider

@Composable
fun HomePage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        HomeContent(navController = navController)
    }
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