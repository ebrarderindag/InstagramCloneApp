package com.example.instagramcloneapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.instagramcloneapp.R

class AddPostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        setContent() {
            AddPostContent()
        }
    }
}
fun AddPostContent(){



}