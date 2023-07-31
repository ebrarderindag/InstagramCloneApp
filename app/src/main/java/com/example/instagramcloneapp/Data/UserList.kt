package com.example.instagramcloneapp.Data

import java.io.Serializable

data class UserList(
    var userList : ArrayList<User> = arrayListOf()
) : Serializable