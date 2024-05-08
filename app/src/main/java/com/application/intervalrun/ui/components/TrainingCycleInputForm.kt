package com.application.intervalrun.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.intervalrun.R
import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.enums.IntervalTypes
import com.application.intervalrun.model.Interval
import com.application.intervalrun.ui.screens.editCycleScreenUI.UserTrainingCycleDetails
import com.application.intervalrun.ui.theme.IntervalRunTheme

@Composable
fun TrainingCycleInputForm(
    userTrainingCycleDetails: UserTrainingCycleDetails,
    modifier: Modifier = Modifier,
    onValueChange: (UserTrainingCycleDetails) -> Unit = {},
    enabled: Boolean = true
) {
    val openIntervalDialog = remember { mutableStateOf(false) }


    fun toggleNewTimerDialog() {
        openIntervalDialog.value = !openIntervalDialog.value
    }

    fun saveNewInterval(currentInterval: Interval) {
        userTrainingCycleDetails.intervals.add(
            currentInterval
        )
        onValueChange(userTrainingCycleDetails)
    }

    fun editCurrentInterval(currentInterval: Interval, index: Int, isDeleting: Boolean) {
        val newIntervals = userTrainingCycleDetails.intervals.toMutableList()

        if (isDeleting) {
            newIntervals.removeAt(index)
        } else {
            newIntervals[index] = currentInterval
        }
        val newUserTrainingCycleDetails = userTrainingCycleDetails.copy(intervals = newIntervals)
        onValueChange(newUserTrainingCycleDetails)
    }
    Column(
        modifier = modifier.padding(horizontal = 2.dp),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = userTrainingCycleDetails.name,
            onValueChange = { onValueChange(userTrainingCycleDetails.copy(name = it)) },
            label = { Text(text = stringResource(R.string.cycle_name)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        userTrainingCycleDetails.intervals.forEachIndexed { index, currentInterval ->
            IntervalCard(currentInterval,
                saveIntervalChanges = { interval: Interval, isDeleting: Boolean ->
                    editCurrentInterval(interval, index, isDeleting)
                })
        }


        Button(
            onClick = { openIntervalDialog.value = !openIntervalDialog.value },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .width(width = 70.dp)
        ) {
            Text("Add Timer")
        }
    }

    if (openIntervalDialog.value) {
        EditTimerDialog(
            toggleEditIntervalDialog = { toggleNewTimerDialog() },
            saveIntervalChanges = { currentInterval: Interval ->
                saveNewInterval(currentInterval)
            },
            currentInterval = Interval(
                intervalId = 0,
                intervalName = "",
                intervalDuration = 0,
                intervalType = IntervalTypes.REST
            ),
            modifier = modifier
        )
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
                intervals = mutableListOf(
                    Interval(
                        intervalId = 0,
                        intervalName = "First Run Timer",
                        intervalDuration = 60
                    ),
                    Interval(intervalId = 1, intervalName = "Rest Timer", intervalDuration = 60)
                ),
                id = 1
            ),
            modifier = Modifier
                .background(color = Color.Gray)

                .fillMaxWidth()
        )
    }
}