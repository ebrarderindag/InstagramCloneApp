package com.example.instagramcloneapp.data


import com.example.instagramclone.data.User
import com.example.instagramcloneapp.R

object DataProvider {
    val user = User(
        id = 1,
        title = "Monty",
        description = "asdfg",
        userImageId = R.drawable.deneme1
    )

    val userList = listOf(
        user,
        User(
            id = 2,
            title = "Jubilee",
            description = "asdfg",
            userImageId = R.drawable.deneme1
        ),
        User(
            id = 3,
            title = "Beezy",
            description = "asdfg",
            userImageId = R.drawable.deneme2
        )
    )
}