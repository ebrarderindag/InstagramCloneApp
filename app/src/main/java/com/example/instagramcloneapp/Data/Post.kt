package com.example.instagramcloneapp.Data

import java.io.Serializable

data class Post(

    val Description: String,
    val ID: Int = 0,
    val URL : String

) : Serializable