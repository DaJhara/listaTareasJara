package com.example.listatareasjara

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listatareasjara.ui.theme.ListaTareasJaraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaTareasJaraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp("Android")
                }
            }
        }
    }
}

@Composable
fun MyApp(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Lista de $name!",
            modifier = modifier.padding(8.dp)
        )
        taskbar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun taskbar() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row() {
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Crear") },
                singleLine = true
            )
            Button(onClick = { /* Do something! */ }, modifier = Modifier.padding(16.dp)) {
                Icon(
                    Icons.Filled.AddCircle,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
        }
        Row() {
            Box(modifier = Modifier.height(500.dp).background(color = Color.LightGray)) {
                Text(text = "Aqui va el box donde se muestra la lista de tareas")
            }

        }
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /* Do something! */ }, modifier = Modifier.padding(16.dp)) {
                Text(text = "Eliminar")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListaTareasJaraTheme {
        MyApp("Tareas")
    }
}

@Preview(showBackground = true)
@Composable
fun taskbarPreview() {
    taskbar()
}



