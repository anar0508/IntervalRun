package com.application.intervalrun.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.application.intervalrun.R
import com.application.intervalrun.data.entities.TrainingCycle

@Composable
fun CycleListItem(cycle: TrainingCycle, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${cycle.cycleName} is template${cycle.isTemplate}" ,
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