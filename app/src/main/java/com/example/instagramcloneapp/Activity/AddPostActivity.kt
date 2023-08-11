package com.example.instagramcloneapp.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.instagramcloneapp.Extensions.getParcelableCompat
import com.example.instagramcloneapp.R
import com.example.instagramcloneapp.data.PostItem
import com.example.instagramcloneapp.data.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class AddPostActivity : ComponentActivity() {
    private var user: Users? = null
    private lateinit var storage : FirebaseStorage
    private  var selectedImg: Uri? = null

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        user = intent?.extras?.getParcelableCompat("user", Users::class.java)
        storage = FirebaseStorage.getInstance()

        setContent() {
            Column {
            TopAppBar(title = { Text(text = "Instagram") })
            AddPostContent()}
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddPostContent() {
        val description = remember { mutableStateOf(TextFieldValue()) }
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
        ){
            Row(Modifier
                .clickable {
                    toGallery()
                    println("Tıklandı")
                }
                .align(Alignment.CenterHorizontally)) {
                UserImage()

            }
            Row(
                Modifier.padding(start = 30.dp)
            )
            {
                TextField(
                    label = { Text(text = "Description") },
                    value = description.value,
                    onValueChange = { description.value = it } )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    //delete selected photo
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Delete")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    //save photo
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Save")
            }
        }
    }

    private fun toGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "Image/*"
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null){
            if (data.data != null){
                selectedImg = data.data!!
                uploadData()
            }
        }
    }

    private fun uploadData() {
        val ref = storage.reference
        selectedImg?.let {
            ref.putFile(it).addOnCompleteListener{
                if (it.isSuccessful){
                    ref.downloadUrl.addOnSuccessListener{task ->
                        uploadInfo(task.toString())
                    }
                }
            }
        }
    }

    private fun uploadInfo(imgURL: String) {
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
        var randomPostId = (0..10).random()
        for (post in user?.postList!!){
            if (post.id == randomPostId){
                randomPostId = (0..10).random()
            }
        }
        val postList = ArrayList<PostItem>()
        postList.add(PostItem(id = randomPostId, description = "", url ="" ))

        database.child(user!!.information?.id.toString()).child("postList").setValue(postList).addOnSuccessListener() {
        }
    }


    @Composable
    private fun UserImage() {
        val image =
            loadPicture(selectedImg.toString(), defaultImage = R.drawable.nonepic).value
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
    fun loadPicture(url: String?, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {
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

        if (url != null){
            Glide.with(LocalContext.current)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) { }
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        bitmapState.value = resource
                    }
                })
        }



        return bitmapState
    }

}


