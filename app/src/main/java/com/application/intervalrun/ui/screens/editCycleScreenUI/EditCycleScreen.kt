package com.application.intervalrun.ui.screens.editCycleScreenUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.intervalrun.R
import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.model.Interval
import com.application.intervalrun.ui.ViewModelProvider
import com.application.intervalrun.ui.components.TopAppBarComponent
import com.application.intervalrun.ui.components.TrainingCycleInputForm
import com.application.intervalrun.ui.theme.IntervalRunTheme
import kotlinx.coroutines.launch

@Composable
fun EditCycleScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditCycleViewModel = viewModel(
        factory = ViewModelProvider.Factory
    )
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState: EditTrainingCycleUiState = viewModel.editTrainingCycleUiState
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarComponent(
                stringResource(id = R.string.new_cycle),
                goingBackEnabled = true,
                navigateBack,
                actions = {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.saveItem()
                                navigateBack()
                            }
                        },
                        enabled = uiState.isEntryValid,

                        modifier = Modifier
                    ) {
                        Text(text = "SAVE")
                    }
                })
        },
    ) { innerPadding ->
        TrainingCycleEntryBody(
            cycleUiState = uiState,
            onItemValueChange = viewModel::updateUiState,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
        ) {
            TrainingCycleInputForm(
                userTrainingCycleDetails = uiState.trainingCycleDetails,
                onValueChange = viewModel::updateUiState,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

@Composable
fun TrainingCycleEntryBody(
    cycleUiState: EditTrainingCycleUiState,
    onItemValueChange: (UserTrainingCycleDetails) -> Unit,
    modifier: Modifier = Modifier
) {

}

