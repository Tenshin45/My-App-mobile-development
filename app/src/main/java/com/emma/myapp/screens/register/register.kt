package com.emma.myapp.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emma.myapp.ViewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("REGISTER",
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
            )
        Spacer(modifier = Modifier.height(16.dp))
        var FullName by remember { mutableStateOf("") }
        var Email by remember { mutableStateOf("") }
        var Password by remember { mutableStateOf("") }
        var ConfirmPassword by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = FullName,
            onValueChange = { FullName = it },
            label = { Text("Full Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null

                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Email,
            onValueChange = { Email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Password,
            onValueChange = { Password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = ConfirmPassword,
            onValueChange = { ConfirmPassword = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        var context = LocalContext.current
        var authViewModel = AuthViewModel(navController, context)
        Button(onClick = { authViewModel.createUser(FullName, Email, Password, ConfirmPassword) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("You already have an account?",
            color = Color.Blue,
            fontSize = 18.sp,
            //fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        TextButton(onClick = { navController.navigate("login_screen") }) {
            Text("Login here")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}
//
//