package com.application.intervalrun

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.application.intervalrun.data.DataSource
import com.application.intervalrun.enums.AppScreens
import com.application.intervalrun.model.Cycle
import com.application.intervalrun.model.TemplateCycle
import com.application.intervalrun.model.UserCycle
import com.application.intervalrun.ui.components.BottomAppBarComponent
import com.application.intervalrun.ui.components.TopAppBarComponent
import com.application.intervalrun.ui.yourCyclesUI.YourCyclesViewModel

@Composable
fun IntervalRunApp(
    viewModel: YourCyclesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = AppScreens.valueOf(
        backStackEntry?.destination?.route ?: AppScreens.UserCycles.name
    )

    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBarComponent() },
        bottomBar = { BottomAppBarComponent(navigateTo  = { navController.navigate(it) }, currentScreenName = currentScreen.name) },
        floatingActionButton = {
            if (currentScreen.name == AppScreens.UserCycles.name) {
                FloatingActionButton(onClick = { }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = AppScreens.UserCycles.name,
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()

        ) {
            composable(route = AppScreens.UserCycles.name){
                Column(
                    modifier = Modifier,
                ) {
                    val userCycles = DataSource().loadUserCycles()
                    Text(text = stringResource(R.string.your_training_cycles), fontSize = 24.sp)
                    YourCyclesListView(userCycles = userCycles, modifier = Modifier)
                }
            }
            composable(route = AppScreens.Templates.name){
                Column(
                    modifier = Modifier,
                ) {
                    val templateCycles = DataSource().loadTemplateCycles()
                    Text(text = stringResource(R.string.template_trainings), fontSize = 24.sp)
                    TemplateCyclesListView(templateCycles = templateCycles, modifier = Modifier)
                }
            }
            composable(route = AppScreens.Wearable.name){
                Column(
                    modifier = Modifier,
                ) {
                    Text(text = stringResource(R.string.wearables), fontSize = 24.sp)
                }
            }
            composable(route = AppScreens.ActiveTimer.name){
                Column(
                    modifier = Modifier,
                ) {
                    Text(text = stringResource(R.string.active_timer), fontSize = 24.sp)
                }
            }
        }
    }


}

@Composable
fun YourCyclesListView(userCycles: MutableList<UserCycle>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(userCycles) {
            CycleListItem(it, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun TemplateCyclesListView(templateCycles: Array<TemplateCycle>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(templateCycles) {
            CycleListItem(it, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun CycleListItem(cycle: Cycle, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = cycle.cycleName,
            fontSize = 18.sp,
            modifier = Modifier.weight(0.6f)
        )
        Row(modifier = Modifier.weight(0.4f), horizontalArrangement = Arrangement.End) {
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
}

@Preview
@Composable
fun IntervalRunAppPreview() {
    IntervalRunApp()
}