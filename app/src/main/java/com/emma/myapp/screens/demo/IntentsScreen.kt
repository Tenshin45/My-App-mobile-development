package com.emma.myapp.screens.demo

import android.content.Intent
import android.net.Uri

import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentScreen(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("INTENTS") },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                )
            )
        }
    )
    { innerpadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "INTENTS",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black
            )
            val context = LocalContext.current
            //OPEN CAMERA
            Button(
                onClick = {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {
                Text("Open  Camera")
            }
            //open browser
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emobilis.ac.ke"))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {
                Text("Open browser")
            }
            //open Google Maps
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nairobi"))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {
                Text("open  maps")
            }
            //open sms
            Button(
                onClick = {
                    val smsintent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0729618566"))
                    smsintent.putExtra("sms_body", "hello from my intent in my app")
                    context.startActivity(smsintent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {

                Text("SEND SMS")
            }
            //open email
            Button(
                onClick = {
                    val emailintent = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", "opera@gmail.com", null)
                    )
                    emailintent.putExtra(Intent.EXTRA_SUBJECT, "HELLO GOODMORNING")
                    context.startActivity(emailintent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {
                Text("send email")
            }
            //open phone dial
            Button(onClick = {
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0729618566"))
                context.startActivity(dialIntent)
            }, modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {
                Text("dial phone")
            }
            //simtoolkit
            Button(
                onClick = {
                    val simToolkitIntent =
                        context.packageManager.getLaunchIntentForPackage("com.android.stk")
                    simToolkitIntent?.let { context.startActivity(it) }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black

                )
            ) {
                Text("stk push")
            }


        }

    }
}
@Preview(showBackground = true)
@Composable
fun IntentScreenPreview() {
    IntentScreen(navController = NavHostController(LocalContext.current))
}
//