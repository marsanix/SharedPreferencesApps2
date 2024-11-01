package com.marsanix.sharedpreferencesapps2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marsanix.sharedpreferencesapps2.ui.theme.SharedPreferencesApps2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedPreferencesApps2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    LoginScreen()
}

@Composable
fun LoginScreen() {

    val context = LocalContext.current
    val sharedPreferencesManager = remember {
        SharedPreferencesManager(context)
    }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // check apakah sudah login
    if(sharedPreferencesManager.isLogin) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    Column(
        modifier
    = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label
        = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {

            sharedPreferencesManager.username = username
            sharedPreferencesManager.isLogin = true
            context.startActivity(Intent(context, MainActivity::class.java))

        },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SharedPreferencesApps2Theme {
        Greeting2("Android")
    }
}