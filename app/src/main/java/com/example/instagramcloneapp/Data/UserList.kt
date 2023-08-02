package com.example.instagramcloneapp.Data

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("User1")
    var userList: ArrayList<User> = arrayListOf()
)