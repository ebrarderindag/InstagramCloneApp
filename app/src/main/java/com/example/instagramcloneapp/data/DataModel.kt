package com.example.instagramcloneapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(
    @SerializedName("Users")
    var Users: ArrayList<Users>? = arrayListOf()
) : Parcelable


