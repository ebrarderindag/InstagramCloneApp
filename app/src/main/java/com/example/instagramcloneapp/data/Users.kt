package com.example.instagramcloneapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    @SerializedName("information")
    val information: Information? = null,
    @SerializedName("postList")
    var postList: ArrayList<PostItem>? = arrayListOf()
) : Parcelable
