package com.example.instagramcloneapp.Activity

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.instagramcloneapp.Extensions.getParcelableCompat
import com.example.instagramcloneapp.R
import com.example.instagramcloneapp.data.PostItem
import com.example.instagramcloneapp.data.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ImageDetailActivity : ComponentActivity() {
    private var user: Users? = null
    private var post: PostItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        user = intent?.extras?.getParcelableCompat("user", Users::class.java)
        post = intent?.extras?.getParcelableCompat("post", PostItem::class.java)

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Column() {
                    ProfileDropMenu()
                    ProfileScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ProfileDropMenu() {
        var expanded by remember { mutableStateOf(false) }
        TopAppBar(
            title = { Text(text = "Instagram") },
            actions =
            {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Delete Post") },
                        onClick = {
                            DeletePost()
                        }
                    )
                }
            }
        )
    }

    @Composable
    fun ProfileScreen() {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints {
                Surface {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        ProfileHeader(containerHeight = this@BoxWithConstraints.maxHeight)
                        ProfileContent(containerHeight = this@BoxWithConstraints.maxHeight)
                    }
                }
            }
        }
    }

    @Composable

    private fun ProfileHeader(containerHeight: Dp) {

        val image =
            loadPicture(post?.url.toString(), defaultImage = R.drawable.nonepic).value
        image?.let { img ->
            Image(

                bitmap = img.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .heightIn(max = containerHeight / 2)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

    }


    @Composable
    fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {
        val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }

        Glide.with(LocalContext.current)
            .asBitmap()
            .load(defaultImage)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    bitmapState.value = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
        Glide.with(LocalContext.current)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    bitmapState.value = resource
                }
            })
        return bitmapState
    }

    @Composable
    private fun ProfileContent(containerHeight: Dp) {
        Column {
            Title()
            post?.description?.let { ProfileProperty(value = it) }
            Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
        }
    }

    @Composable
    private fun Title() {
        Column(modifier = Modifier.padding(16.dp)) {
            user?.information?.userName?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }
    }

    @Composable
    private fun ProfileProperty(value: String) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Divider(modifier = Modifier.padding(bottom = 4.dp))
            Text(
                text = value,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Visible
            )
        }
    }

    private fun DeletePost() {
        var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
        database.child(post!!.id.toString()).removeValue()
    }


}