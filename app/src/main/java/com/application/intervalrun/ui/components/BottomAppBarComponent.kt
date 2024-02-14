package com.application.intervalrun.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.application.intervalrun.R
import com.application.intervalrun.enums.AppScreens

@Composable
fun BottomAppBarComponent (navigateTo: (routePath: String)-> Unit, currentScreenName: String){

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Log.d("MainActivity", "BottomAppBarComponent currentScreenName: $currentScreenName" )
            Log.d("MainActivity", "BottomAppBarComponent AppScreens.UserCycles.name: ${AppScreens.UserCycles.name}" )
            Log.d("MainActivity", "BottomAppBarComponent AppScreens.UserCycles.name: ${AppScreens.ActiveTimer.name}" )
            IconButton(onClick = { navigateTo(AppScreens.UserCycles.name) }) {
                Icon(
                    if(currentScreenName == AppScreens.UserCycles.name) {
                        Icons.Filled.TaskAlt
                    } else {
                        Icons.Outlined.TaskAlt
                           },
                    contentDescription = stringResource(
                        id = R.string.your_training_cycles
                    )
                )
            }
            IconButton(onClick = { navigateTo(AppScreens.ActiveTimer.name) }) {
                Icon(
                    if(currentScreenName == AppScreens.ActiveTimer.name) {
                        Icons.Filled.Timer
                    } else {
                        Icons.Outlined.Timer
                    },
                    contentDescription = stringResource(
                        id = R.string.active_timer
                    )
                )
            }
            IconButton(onClick = { navigateTo(AppScreens.Wearable.name) }) {
                Icon(
                    if(currentScreenName == AppScreens.Wearable.name) {
                        Icons.Filled.Watch
                    } else {
                        Icons.Outlined.Watch
                    },
                    contentDescription = stringResource(R.string.wearables)
                )
            }

        }

    }
}