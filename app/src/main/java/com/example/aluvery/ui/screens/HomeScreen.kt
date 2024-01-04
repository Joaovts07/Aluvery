package com.example.aluvery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.model.Product
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.components.SeachTextField

class HomeScreenUiState() {

    var text by mutableStateOf("")


    val searchedProduct get() =
        if(text.isNotBlank()) {
            sampleProducts.filter { product ->
                product.name.contains(
                    text,
                    ignoreCase = true
                ) || product.description?.contains(
                    text,
                    ignoreCase = true
                ) ?: false
            }
        } else emptyList()

    fun isShowSection(): Boolean =  text.isBlank()
}

@Composable
fun HomeScreen(
    sections: Map<String,List<Product>>,
    searchText: String = ""
) {
    Column {
        val state = remember {
            HomeScreenUiState()
        }
        val searchedProduct = remember(state.text) {
            state.searchedProduct
        }
        SeachTextField(searchText = state.text, onSearchChange = {
            state.text = it
        })
        /*val sampleProductFiltered = remember(text) {
            searchProducts(text)
        } */

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSection()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title, products = products
                        )
                    }
                }
            } else {
                items(searchedProduct) { product ->
                    CardProductItem(
                        product = product,
                        Modifier.padding(horizontal = 32.dp),
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(sampleSections)
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreviewWithFilter() {
    HomeScreen(
        sampleSections,
        "pizza")
}