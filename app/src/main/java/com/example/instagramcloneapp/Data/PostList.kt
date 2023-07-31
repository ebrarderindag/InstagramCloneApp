package com.example.instagramcloneapp.Data

import java.io.Serializable

data class PostList(

    var Post : ArrayList<Post> = arrayListOf()
    ) : Serializable