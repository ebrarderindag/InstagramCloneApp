package com.example.instagramcloneapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.data.User
import com.example.instagramcloneapp.R
import com.example.instagramcloneapp.data.DataProvider

class ImageDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        setContent {
            ImageDetail()
        }
    }
}

@Composable
fun ImageDetail() {

    Box(modifier = Modifier.fillMaxSize()) {
        ProfileScreen(user = DataProvider.user)
    }
}


@Composable
fun ProfileScreen(user: User){
    val scrollState  = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                ) {
                    //TODO:
                    ProfileHeader(user = user, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(user = user, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}
@Composable

private fun ProfileHeader(
    user: User,
    containerHeight: Dp
){
    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        painter = painterResource(id = user.ImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}
@Composable
private fun ProfileContent(user: User, containerHeight: Dp){
    Column {
        //TODO:
        Title(user = user)

        ProfileProperty( value = user.description )
        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Title(user: User){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = user.nickname,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp

        )

    }
}
@Composable
private fun ProfileProperty(value: String){
    Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)){
        Divider(modifier = Modifier.padding(bottom = 4.dp))

        Text(
            text =value,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Visible
        )
    }
}