package com.emma.myapp.screens.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emma.myapp.R

@Composable
fun RowScreen(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.LightGray)
            .padding(8.dp),
        //horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Image
        Image(painter = painterResource(id = R.drawable.logo1),
            contentDescription = "logo",
            modifier = Modifier
                .padding(8.dp)
                .height(200.dp)
                .clip(CircleShape)

        )
        Text("Hello ! Welcome to my app",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            )
        //Image(painter = painterResource(id = R.drawable.logo1),
         //   contentDescription = "logo",
           // modifier = Modifier
                //.height(150.dp)
             //   .padding(8.dp)
            //)
    }
}
@Preview(showBackground = true)
@Composable
fun RowScreenPreview(){
    RowScreen()
}
//