package com.example.myalarm


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myalarm.ui.theme.MyAlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAlarmTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // State to hold lists of alarms
    var alarms by remember { mutableStateOf(listOf("Alarm 1", "Alarm 2", "Alarm 3")) }
    var newAlarmText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Alarm App") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (newAlarmText.isNotEmpty()) {
                    alarms = alarms + newAlarmText
                    newAlarmText = ""
                }
            }) {
                Text("+")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            AlarmList(alarms)
            Spacer(modifier = Modifier.height(16.dp))
            AddAlarmField(newAlarmText) { text ->
                newAlarmText = text
            }
        }
    }
}

@Composable
fun AlarmList(alarms: List<String>) {
    LazyColumn {
        items(alarms) { alarm ->
            Text(text = alarm, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun AddAlarmField(
    alarmText: String,
    onAlarmTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = alarmText,
        onValueChange = onAlarmTextChanged,
        label = { Text("Add New Alarm") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAlarmTheme {
        MainScreen()
    }
}
