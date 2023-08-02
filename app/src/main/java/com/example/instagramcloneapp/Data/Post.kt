package com.example.instagramcloneapp.Data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Post(
    @SerializedName("Description")
    val Description: String,
    @SerializedName("ID")
    val ID: Int = 0,
    @SerializedName("URL")
    val URL : String

): Serializable