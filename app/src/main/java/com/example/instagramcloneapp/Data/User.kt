package com.example.instagramclone.data

import java.io.Serializable

data class User(
    val id : Int,
    val title: String,
    val description: String,
    val userImageId: Int = 0
) : Serializable