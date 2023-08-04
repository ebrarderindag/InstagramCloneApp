package com.example.instagramcloneapp.Data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatabaseModel(
    @SerializedName("Field")
    var Field: String = "",
    @SerializedName("Users")
    var Users: ArrayList<Users>? = arrayListOf()
) :Parcelable{
    constructor():this("", arrayListOf())
}
@Parcelize
data class Users(
    @SerializedName("Information")
    val Information: Information,
    @SerializedName("PostList")
    var PostList: ArrayList<PostList>?
):Parcelable{
    constructor():this(Information(), arrayListOf())
}
@Parcelize
data class Information(
    @SerializedName("ID")
    val ID: Int? = null,
    @SerializedName("Password")
    val Password: String? = null,
    @SerializedName("UserName")
    val UserName: String? = null
):Parcelable
@Parcelize
data class PostList(
    @SerializedName("Description")
    val Description: String? = null,
    @SerializedName("ID")
    val ID: Int? = null,
    @SerializedName("URL")
    val URL: String? = null
):Parcelable
