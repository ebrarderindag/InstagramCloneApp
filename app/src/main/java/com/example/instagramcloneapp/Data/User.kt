package com.example.instagramcloneapp.Data

import java.io.Serializable

data class User(
    val information: Information,
    var PostList : ArrayList<PostList> = arrayListOf()

) : Serializable{
    data class Information(val ID : Int,
                           val nickname: String,
                           val password: String)
}

