package com.application.intervalrun.ui.screens.editCycleScreenUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.intervalrun.IntervalRunApp
import com.application.intervalrun.R
import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.model.Timer
import com.application.intervalrun.ui.ViewModelProvider
import com.application.intervalrun.ui.components.TopAppBarComponent
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
        modifier = Modifier,
        topBar = {
            TopAppBarComponent(
                stringResource(id = R.string.new_cycle), goingBackEnabled = true, navigateBack
            )
        },
    ) { innerPadding ->
        TrainingCycleEntryBody(
            cycleUiState = uiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun TrainingCycleEntryBody(
    cycleUiState: EditTrainingCycleUiState,
    onItemValueChange: (UserTrainingCycleDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        TrainingCycleInputForm(
            userTrainingCycleDetails = cycleUiState.trainingCycleDetails,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = cycleUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingCycleInputForm(
    userTrainingCycleDetails: UserTrainingCycleDetails,
    modifier: Modifier = Modifier,
    onValueChange: (UserTrainingCycleDetails) -> Unit = {},
    enabled: Boolean = true
) {
    val scope = rememberCoroutineScope()
    val openTimerDialog = remember { mutableStateOf(false) }
    val newTimer = remember { mutableStateOf(Timer()) }
    fun dismissNewTimer (){
        openTimerDialog.value = !openTimerDialog.value
        newTimer.value = Timer()
    }
    fun saveNewTimer (){
        openTimerDialog.value = !openTimerDialog.value
        userTrainingCycleDetails.timers.add(newTimer.value);
        onValueChange(userTrainingCycleDetails)
        newTimer.value = Timer()
    }
    Column(
        modifier = modifier.padding(horizontal = 2.dp),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = userTrainingCycleDetails.name,
            onValueChange = { onValueChange(userTrainingCycleDetails.copy(name = it)) },
            label = { Text(text = stringResource(R.string.cycle_name)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        userTrainingCycleDetails.timers.forEach {
            Card(modifier = modifier.fillMaxWidth()) {
                Text(text = it.timerName)
            }
        }

        Button(
            onClick = { openTimerDialog.value = !openTimerDialog.value },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .width(width = 70.dp)
        ) {
            Text("Add Timer")
        }
    }

    // Todo for every field create a mutable state. Only on confirm add the new Timer
    if (openTimerDialog.value) {
        Dialog(onDismissRequest = { dismissNewTimer() }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        value = newTimer.value.timerName,
                        onValueChange = { newTimer.value.copy(timerName = it) },
                        label = { Text(text = stringResource(R.string.timer_name)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_medium)),
                        enabled = enabled,
                        singleLine = true
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    TextButton(
                        onClick = { dismissNewTimer() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { saveNewTimer() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TrainingCycleInputFormPreview() {
    IntervalRunTheme {
        TrainingCycleInputForm(
            userTrainingCycleDetails = UserTrainingCycleDetails(
                name = "First Cycle",
                cycleState = CycleState.STOPPED,
                isTemplate = false,
                timers = mutableListOf(
                    Timer(timerId = 0, timerName = "First Run Timer", timerDuration = 60),
                    Timer(timerId = 1, timerName = "Rest Timer", timerDuration = 60)
                ),
                id = 1
            ),
            modifier = Modifier
                .background(color = Color.Gray)

                .fillMaxWidth()
        )
    }
}