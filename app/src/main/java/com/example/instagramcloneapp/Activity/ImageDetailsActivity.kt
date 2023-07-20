package com.example.instagramcloneapp.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.instagramclone.data.User
import com.example.instagramcloneapp.R
import com.example.instagramcloneapp.Screen.ProfileScreen
import com.example.instagramcloneapp.ui.theme.InstagramCloneAppTheme


class ImageDetailsActivity : ComponentActivity() {
    private val user: User by lazy {
        intent?.getSerializableExtra(USER_ID) as User

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneAppTheme() {
                ProfileScreen(user = user)
            }
        }
    }
    companion object{
        private const val USER_ID = "user_id"
        fun newIntent (context: Context, user: User) =
            Intent(context, ImageDetailsActivity::class.java).apply{
                putExtra(USER_ID, user)
            }
    }
}