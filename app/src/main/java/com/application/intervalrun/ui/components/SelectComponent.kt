package com.application.intervalrun.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box

import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.application.intervalrun.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SelectComponent(
    options: List<T>,
    selectedValue: MutableState<T>,
    label: String,
    modifier: Modifier = Modifier,
    handleValueChange: (newValue: T) -> Unit,
) {

    val expanded = remember { mutableStateOf(false) }
    val expandedThroughIcon = remember { mutableStateOf(false) }
    val searchValue = remember {
        mutableStateOf(selectedValue.value.toString().lowercase(Locale.ROOT)
            .replaceFirstChar { it.uppercase() })
    }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = it },
        modifier = modifier
            .fillMaxWidth()

    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = searchValue.value,
            onValueChange = {
                searchValue.value = it
                expanded.value = true
                expandedThroughIcon.value = false
            },
            singleLine = true,
            label = { Text(text = label) },
            trailingIcon = {
                if (expanded.value) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropUp,
                        contentDescription = "Expanded icon",
                        modifier = Modifier.clickable {
                            expanded.value = false
                            expandedThroughIcon.value = false
                        }
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown icon",
                        modifier = Modifier.clickable {
                            expanded.value = true
                            expandedThroughIcon.value = true
                        }
                    )
                }
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.heightIn(min = 48.dp, max = 144.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical, )
        ) {
            options.forEach { option ->
                if (expandedThroughIcon.value || option.toString()
                        .contains(searchValue.value.uppercase(Locale.ROOT))
                ) {
                    val optionText = option.toString().lowercase(Locale.ROOT)
                        .replaceFirstChar { it.uppercase() }
                    DropdownMenuItem(
                        text = { Text(text = optionText, modifier = modifier) },
                        onClick = {
                            searchValue.value = optionText
                            expanded.value = false
                            handleValueChange(option)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                    HorizontalDivider()
                }
            }
        }
    }

}