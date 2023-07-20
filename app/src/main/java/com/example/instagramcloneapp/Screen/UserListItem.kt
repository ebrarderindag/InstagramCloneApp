package com.example.instagramcloneapp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramclone.data.User
import com.example.instagramcloneapp.Routes

@Composable
fun UserListItem(user: User,navController: NavHostController){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {

        Row (Modifier
            .clickable { navController.navigate(Routes.ImageDetail.route) }
            .align(Alignment.CenterHorizontally)){
            UserImage(user = user)
        }
        Row(
            Modifier
            .padding(start = 30.dp))
        {

            Text(text = "@" + "${user.title}",style = MaterialTheme.typography.titleMedium, fontSize = 30.sp)
        }


        Row(
            Modifier
            .padding(start = 30.dp,end= 16.dp, top = 16.dp, bottom = 20.dp) )
        {

            Text(text = "----Text----",style = MaterialTheme.typography.titleMedium, fontSize = 16.sp)
        }
    }
}

@Composable
private fun UserImage(user: User){
    Image(
        painter = painterResource(id = user.userImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}