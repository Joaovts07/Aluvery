package com.example.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.model.Product
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.components.SearchTextField

class HomeScreenUiState(
    val searchText: String = "",
    val sections: Map<String,List<Product>> = emptyMap(),
    val searchdProducts: List<Product> = emptyList(),
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSection(): Boolean =  searchText.isBlank()

}

@Composable
fun HomeScreen(products: List<Product>) {

    val sections = mapOf(
        "Todos os Produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by remember {
        mutableStateOf("")
    }

    fun containsInNameOrDescription(): (Product) -> Boolean = { product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) || product.description?.contains(
            text,
            ignoreCase = true
        ) ?: false
    }

    val searchedProduct = remember(text, products) {
        if(text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) + products.filter(containsInNameOrDescription())
        } else emptyList()
    }

    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchdProducts = searchedProduct,
            searchText = text
        ) {
            text = it
        }
    }
    HomeScreen(state = state)
}
@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {

        val sections = state.sections
        SearchTextField(
            searchText = state.searchText,
            onSearchChange = state.onSearchChange,
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )

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
                items(state.searchdProducts) { product ->
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
    HomeScreen(HomeScreenUiState(sections = sampleSections))
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreviewWithFilter() {
    HomeScreen(
        HomeScreenUiState("a", sampleSections))
}