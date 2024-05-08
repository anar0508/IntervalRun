package com.application.intervalrun.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.intervalrun.enums.IntervalTypes
import com.application.intervalrun.model.Interval
import com.application.intervalrun.util.TimeFormatUtil

@Composable
fun IntervalCard(
    interval: Interval,
    modifier: Modifier = Modifier,
    showStart: Boolean = false,
    saveIntervalChanges: (Interval, Boolean) -> Unit,
) {

    val showMoreMenu = remember { mutableStateOf(false) }
    val showIntervalEdit = remember { mutableStateOf(false) }
    val isResting = interval.intervalType == IntervalTypes.REST
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isResting) {
                Color(0xFF3E691A)
            } else {
                MaterialTheme.colorScheme.errorContainer
            },
            contentColor = if (isResting) {
                MaterialTheme.colorScheme.inverseOnSurface
            } else {
                MaterialTheme.colorScheme.onErrorContainer
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 110.dp)
    ) {
        Column(
            modifier = modifier.padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.End
            ) {
                Box {
                    IconButton(
                        onClick = { showMoreMenu.value = !showMoreMenu.value },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Start Interval",
                            modifier = Modifier
                        )
                    }
                    DropdownMenu(
                        expanded = showMoreMenu.value,
                        onDismissRequest = { showMoreMenu.value = false },
                        modifier = Modifier
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Interval",
                                    modifier = Modifier
                                )
                            },
                            onClick = { showIntervalEdit.value = true
                                      showMoreMenu.value = false },
                            modifier = Modifier
                        )
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Interval",
                                    modifier = Modifier
                                )
                            },
                            onClick = { saveIntervalChanges(interval, true) },
                            modifier = Modifier
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier) {
                    Text(
                        text = interval.intervalName,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.weight(6f)
                    )
                    Text(
                        text = TimeFormatUtil.formatDurationInNumberToHMS(interval.intervalDuration),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    if (showStart) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.PlayCircle,
                                contentDescription = "Start Interval",
                                modifier = Modifier.size(70.dp)
                            )
                        }
                    }
                }

            }
            if (showIntervalEdit.value) {
                EditTimerDialog(
                    toggleEditIntervalDialog = { showIntervalEdit.value = !showIntervalEdit.value },
                    saveIntervalChanges = { currentInterval: Interval ->
                        saveIntervalChanges(currentInterval, false)
                    },
                    currentInterval = interval.copy(),
                    modifier = modifier
                )
            }
        }
    }
}

@Preview
@Composable
fun IntervalCardPreview() {
    IntervalCard(
        Interval(
            intervalName = "First Run",
            intervalType = IntervalTypes.REST,
            intervalDuration = 180
        ), showStart = false,
        saveIntervalChanges = { _, _ -> }
    )
}