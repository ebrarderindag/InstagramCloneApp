package com.example.instagramcloneapp.Data

import java.io.Serializable

data class PostList(

    val description: String,
    val ImageId: Int = 0,
    val URL : String

    ) : Serializable