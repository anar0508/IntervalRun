package com.application.intervalrun.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.application.intervalrun.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    appBarText: String,
    goingBackEnabled: Boolean = false,
    navigateUp: () -> Unit = {},
    actions: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(appBarText)
        },
        navigationIcon = {
            if (goingBackEnabled) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.to_previous_screen)
                    )
                }
            }
        },
        actions = { actions() }
    )
}