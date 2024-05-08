package com.application.intervalrun.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.application.intervalrun.R
import com.application.intervalrun.enums.IntervalTypes
import com.application.intervalrun.model.Interval
import com.application.intervalrun.util.TimeFormatUtil

@Composable
fun EditTimerDialog(
    toggleEditIntervalDialog: () -> Unit,
    saveIntervalChanges: (Interval) -> Unit,
    currentInterval: Interval,
    modifier: Modifier = Modifier,
) {
    val newIntervalName = remember { mutableStateOf(currentInterval.intervalName) }
    val newIntervalDuration = remember { mutableIntStateOf(currentInterval.intervalDuration) }
    val newIntervalType = remember { mutableStateOf(currentInterval.intervalType) }
    fun resetIntervalForm() {
        toggleEditIntervalDialog()
        newIntervalName.value = ""
        newIntervalDuration.intValue = 0
        newIntervalType.value = IntervalTypes.RUNNING
    }
    Dialog(onDismissRequest = { toggleEditIntervalDialog() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
            ) {
                OutlinedTextField(
                    value = newIntervalName.value,
                    onValueChange = { newIntervalName.value = it },
                    label = { Text(text = stringResource(R.string.interval_name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                    singleLine = true
                )
                OutlinedTextField(
                    value = TextFieldValue(
                        text = TimeFormatUtil.formatDurationInNumberToHMS(
                            newIntervalDuration.intValue
                        ), selection = TextRange(11)
                    ),
                    onValueChange = {
                        if (it.text.length<=12 && it.text[0] == '0') {
                            newIntervalDuration.intValue =
                                TimeFormatUtil.formatDurationFromHMSToNumber(it.text)
                        }
                    },
                    label = { Text(text = stringResource(R.string.interval_duration))},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.nativeKeyEvent.keyCode == Key.Backspace.nativeKeyCode) {
                                newIntervalDuration.intValue /= 10
                                true
                            } else {
                                false
                            }
                        },
                    singleLine = true,
                )
                SelectComponent(
                    options = IntervalTypes.entries,
                    selectedValue = newIntervalType,
                    handleValueChange = { newIntervalType.value = it },
                    label = stringResource(R.string.interval_type),
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                TextButton(
                    onClick = { resetIntervalForm() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Dismiss")
                }
                TextButton(
                    onClick = {
                        val intervalName =
                            newIntervalName.value.ifBlank { newIntervalType.value.toString() }
                        currentInterval.intervalName = intervalName
                        currentInterval.intervalType = newIntervalType.value
                        currentInterval.intervalDuration = newIntervalDuration.intValue
                        saveIntervalChanges(currentInterval)
                        resetIntervalForm()
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}