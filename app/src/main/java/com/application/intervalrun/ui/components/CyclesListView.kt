package com.application.intervalrun.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.application.intervalrun.data.entities.TrainingCycle

@Composable
fun CyclesListView(
    cyclesToShow: List<TrainingCycle>,
    itemContent: @Composable (TrainingCycle) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(cyclesToShow.size) { index ->
            itemContent(cyclesToShow[index])
        }
    }
}