package com.application.intervalrun.ui.screens.userCyclesScreenUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.intervalrun.R
import com.application.intervalrun.enums.AppScreens
import com.application.intervalrun.ui.components.CyclesListView
import com.application.intervalrun.ui.ViewModelProvider
import com.application.intervalrun.ui.components.BottomAppBarComponent
import com.application.intervalrun.ui.components.CycleListItem
import com.application.intervalrun.ui.components.TopAppBarComponent

@Composable
fun UserCyclesScreen(
    navigateToTrainingCycleView: (Int) -> Unit,
    navigateToNewTrainingCycleView: () -> Unit,
    navigateTo: (routePath: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserCyclesViewModel = viewModel(
        factory = ViewModelProvider.Factory
    )
) {
    val uiState by viewModel.userTrainingCyclesUiState.collectAsState()
    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBarComponent(stringResource(id = R.string.your_training_cycles)) },
        bottomBar = {
            BottomAppBarComponent(
                navigateTo = navigateTo,
                currentScreenName = AppScreens.UserCycles.name
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToNewTrainingCycleView() }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
        ) {
            CyclesListView(cyclesToShow = uiState.userCycles, { cycle ->
                CycleListItem(cycle, modifier = Modifier.padding(4.dp))
            }
            )
        }
    }
}