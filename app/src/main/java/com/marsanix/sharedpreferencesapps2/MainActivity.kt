package com.marsanix.sharedpreferencesapps2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marsanix.sharedpreferencesapps2.ui.theme.SharedPreferencesApps2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedPreferencesApps2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val fileModel = FileModel()
    val fileStorageHelper = FileStorageHelper()
    val sharedPreferencesManager = remember {
        SharedPreferencesManager(context)
    }

    fileModel.fileName = "latihanFileStorage.txt"
    fileModel.data = "Belajar Pemrograman Android Kotlin untuk membuat dan membaca file text"
    fileStorageHelper.writeToFile(fileModel, context)

    val data = fileStorageHelper.readFromFile("latihanFileStorage.txt", context)

    Column(modifier = modifier.padding(16.dp)) {

        Spacer(modifier = modifier.height(50.dp))

        val dataUser = sharedPreferencesManager.username;
        Text("Hello $dataUser")

        Text(
            text = "Isi dari file ${data.fileName}:\n${data.data}",
            modifier = modifier
        )

        Button(onClick = {
            sharedPreferencesManager.clear()
            context.startActivity(Intent(context, LoginActivity::class.java))
        }) {
            Text("Logout")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SharedPreferencesApps2Theme {
        Greeting("Android")
    }
}