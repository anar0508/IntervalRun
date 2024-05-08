package com.application.intervalrun

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.application.intervalrun.enums.AppScreens
import com.application.intervalrun.ui.screens.editCycleScreenUI.EditCycleScreen
import com.application.intervalrun.ui.screens.userCyclesScreenUI.UserCyclesScreen

@Composable
fun IntervalRunApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = AppScreens.valueOf(
        backStackEntry?.destination?.route ?: AppScreens.UserCycles.name
    )

    Scaffold(
        modifier = Modifier

        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.UserCycles.name,
            modifier = Modifier
                .padding(innerPadding)

        ) {
            composable(route = AppScreens.UserCycles.name) {
                UserCyclesScreen(
                    navigateToTrainingCycleView = {
                        navController.navigate(
                            "${AppScreens.TrainingCycle.name}/${it}"
                        )
                    },
                    navigateToNewTrainingCycleView = { navController.navigate(AppScreens.NewCycle.name) },
                    navigateTo = { navController.navigate(it) } )
            }
            composable(route = AppScreens.Templates.name) {
                Column(
                    modifier = Modifier,
                ) {
//                    val templateCycles = DataSource().loadTemplateCycles()
//                    Text(text = stringResource(R.string.template_trainings), fontSize = 24.sp)
//                    TemplateCyclesListView(templateCycles = templateCycles, modifier = Modifier)
                }
            }
            composable(route = AppScreens.Wearable.name) {
                Column(
                    modifier = Modifier,
                ) {
                    Text(text = stringResource(R.string.wearables), fontSize = 24.sp)
                }
            }
            composable(route = AppScreens.ActiveInterval.name) {
                Column(
                    modifier = Modifier,
                ) {
                    Text(text = stringResource(R.string.active_interval), fontSize = 24.sp)
                }
            }
            composable(route = AppScreens.NewCycle.name) {
                EditCycleScreen(navigateBack = { navController.navigateUp() }, modifier = Modifier,)
            }
        }
    }


}

@Preview
@Composable
fun IntervalRunAppPreview() {
    IntervalRunApp()
}