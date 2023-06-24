package com.example.listatareasjara

import android.os.Bundle
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    MyApp("Tareas")
                }
            }
        }
    }
}

data class task(val description: String, var isComplete: Boolean)

@Composable
fun MyApp(name: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.background(color = Color.DarkGray).fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()){
            Text(
                text = "Lista de $name!",
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp
            )
        }
        taskbar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun taskbar() {
    val taskList = remember { mutableStateListOf<task>() }
    val taskText = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(color = Color.LightGray)) {
        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            var text by remember { mutableStateOf("") }
            Box(modifier = Modifier.weight(0.7f)
            ) {
                OutlinedTextField(
                    value = taskText.value,
                    onValueChange = {taskText.value = it},
                    modifier = Modifier.background(Color.White).fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier.weight(0.3f)
            ) {
                Button(
                    onClick = {
                        if (taskText.value.isNotBlank()) {
                            taskList.add(task(taskText.value, false))
                            taskText.value = ""
                        }
                    }, modifier = Modifier.padding(16.dp)) {
                    Icon(
                        Icons.Filled.AddCircle,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        Row() {
            Box(modifier = Modifier.height(500.dp).background(color = Color.White)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(taskList) { task ->
                        val checked = remember { mutableStateOf(task.isComplete) }
                        Row(modifier = Modifier.padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.weight(0.9f)
                            ) {
                                Text(
                                    text = task.description,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            Box(
                                modifier = Modifier.weight(0.1f)
                            ) {
                                Checkbox(
                                    checked = checked.value,
                                    onCheckedChange = { newValue ->
                                        checked.value = newValue
                                        taskList.find { it == task }?.isComplete = newValue
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                taskList.removeIf { it.isComplete }
            }, modifier = Modifier.padding(16.dp)) {
                Text(text = "Eliminar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ListaTareasJaraTheme {
        MyApp("Tareas")
    }
}

@Preview(showBackground = true)
@Composable
fun taskbarPreview() {
    taskbar()
}
