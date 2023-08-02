package com.example.instagramcloneapp.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("Information")
    val information: Information,
    @SerializedName("PostList")
    var PostList : ArrayList<PostList> = arrayListOf()
)  {
    data class Information(
        @SerializedName("Id")
        val Id : Int,
        @SerializedName("Password")
        val Password: String,
        @SerializedName("Username")
        val Username: String
                           )
}

