package com.example.instagramcloneapp.Data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    @SerializedName("Users")
    var Users: ArrayList<Users>? = arrayListOf()
) : Parcelable {
    constructor():this(arrayListOf())
}
@Parcelize
data class Users(
    @SerializedName("information")
    val information: Information? = null,
    @SerializedName("postList")
    var postList: ArrayList<PostList>? = arrayListOf()
) : Parcelable {
    constructor() : this(Information(), arrayListOf())
}
@Parcelize
data class Information(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("userName")
    val userName: String? = null
) : Parcelable

@Parcelize
data class PostList(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("url")
    val url: String? = null
) : Parcelable