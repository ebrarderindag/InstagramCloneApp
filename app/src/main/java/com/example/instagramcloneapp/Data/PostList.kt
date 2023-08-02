package com.example.instagramcloneapp.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostList(
    @SerializedName("Post1")
    var Post : ArrayList<Post> = arrayListOf()
    )