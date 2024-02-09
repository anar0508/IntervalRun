package com.application.intervalrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.intervalrun.model.Cycle
import com.application.intervalrun.model.UserCycle
import com.application.intervalrun.ui.theme.IntervalRunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntervalRunTheme(darkTheme = true) {
                IntervalRunApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntervalRunApp() {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Interval Run ")
                }
            )
        },
        bottomBar = {
            BottomAppBar(

                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painterResource(id = R.drawable.timer),
                            contentDescription = stringResource(
                                id = R.string.your_training_cycles
                            )
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painterResource(id = R.drawable.timer),
                            contentDescription = "ActiveTimer"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painterResource(id = R.drawable.timer),
                            contentDescription = "Wearable"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painterResource(id = R.drawable.timer),
                            contentDescription = "Templates"
                        )
                    }
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = Modifier,
            ) {
                Text(text = stringResource(R.string.your_training_cycles), fontSize = 24.sp)
            }
        }

    }

}

@Composable
fun YourCyclesListView(userCycles: MutableList<UserCycle>, modifier: Modifier = Modifier) {
    LazyColumn( modifier = modifier){
        items(userCycles) {
            CycleListItem(it, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun CycleListItem(cycle: Cycle, modifier: Modifier = Modifier) {
    Row (modifier = modifier){
        Text(cycle.cycleName);
        IconButton(onClick = { /* do something */ }) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = stringResource(R.string.start_cycle)
            )
        }
        IconButton(onClick = { /* do something */ }) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(R.string.edit_cycle)
            )
        }
        IconButton(onClick = { /* do something */ }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.delete_cycle)
            )
        }
    }
}

@Preview
@Composable
fun IntervalRunAppPreview() {
    IntervalRunApp()
}

