package com.example.instagramcloneapp.Data

import java.io.Serializable

data class User(
    val information: Information,
    var PostList : ArrayList<PostList> = arrayListOf()

) : Serializable{
    data class Information(val Id : Int,
                           val Password: String,
                           val Username: String
                           ) : Serializable
}

