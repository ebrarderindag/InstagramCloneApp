package com.example.instagramclone.data

import com.example.instagramcloneapp.Data.PostList
import java.io.Serializable

data class User(

    val ID : Int,
    val nickname: String,
    val password: String,
    var PostList : ArrayList<PostList> = arrayListOf()

) : Serializable