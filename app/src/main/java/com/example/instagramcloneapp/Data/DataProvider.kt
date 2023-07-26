package com.example.instagramcloneapp.data


import com.example.instagramclone.data.User
import com.example.instagramcloneapp.R

object DataProvider {
    val user = User(
        id = 1,
        username = "Monty",
        description = "asdfg",
        userImageId = R.drawable.deneme1
    )

    val userList = listOf(
        user,
        User(
            id = 2,
            username = "Jubilee",
            description = "asdfg",
            userImageId = R.drawable.deneme1
        ),
        User(
            id = 3,
            username = "Beezy",
            description = "asdfg",
            userImageId = R.drawable.deneme2
        )
    )
}