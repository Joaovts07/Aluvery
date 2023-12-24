package com.example.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeachTextField(searchText: String,
                   onSearchChange: (String)-> Unit) {

    OutlinedTextField(value = searchText, onValueChange = { newValue ->
        onSearchChange(newValue)
    },
        Modifier
            .padding(
                16.dp
            )
            .fillMaxWidth(), shape = RoundedCornerShape(100), leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        }, label = {
            Text(text = "Produto")
        }, placeholder = {
            Text(text = "O que você procura?")
        })
}