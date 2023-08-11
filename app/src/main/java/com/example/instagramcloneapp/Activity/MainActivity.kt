package com.example.instagramcloneapp.Activity


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.instagramcloneapp.data.PostItem
import com.example.instagramcloneapp.data.Users
import com.example.instagramcloneapp.Extensions.getParcelableCompat
import com.example.instagramcloneapp.R
import com.example.instagramcloneapp.ui.theme.InstagramCloneAppTheme

class MainActivity : ComponentActivity() {
    private var user: Users? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent?.extras?.getParcelableCompat("user", Users::class.java)
        println("Data: $user")

        setContent {
            InstagramCloneAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        OptionMenu()
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {}
                            }
                            user?.postList?.let {
                                items(1) {
                                    for (index in user?.postList!!) {
                                        UserListItem(index)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OptionMenu() {
        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }
        val activity = (LocalContext.current as? MainActivity)

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
                        text = { Text("Add Post") },
                        onClick = {
                            val intent = Intent(context, AddPostActivity::class.java)
                            val bundle = Bundle()
                            bundle.putParcelable("user", user)
                            intent.putExtras(bundle)
                            context.startActivity(intent)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                            activity?.finish()
                        }
                    )
                }
            }
        )
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun UserListItem(index: PostItem) {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Row(Modifier
                .clickable {
                    val intent = Intent(context, ImageDetailActivity()::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable("user", user)
                    bundle.putParcelable("post", index)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
                .align(Alignment.CenterHorizontally)) {
                UserImage(index)
            }
            Row(
                Modifier
                    .padding(start = 30.dp)
            )
            {
                Text(
                    text = "@" + "${user?.information?.userName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 30.sp
                )
            }
            Row(
                Modifier
                    .padding(start = 30.dp, end = 16.dp, top = 16.dp, bottom = 20.dp)
            )
            {
                index.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    @Composable
    private fun UserImage(index: PostItem) {

        val image =
            loadPicture(index.url.toString(), defaultImage = R.drawable.nonepic).value
        image?.let { img ->
            Image(
                bitmap = img.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(350.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
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
}