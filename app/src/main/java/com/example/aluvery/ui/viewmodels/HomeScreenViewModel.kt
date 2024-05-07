package com.example.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aluvery.dao.ProductDao
import com.example.aluvery.model.Product
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.ui.states.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()
    var uiState: HomeScreenUiState by mutableStateOf(
        HomeScreenUiState(
            sections = mapOf(
                "Todos os Produtos" to dao.products(),
                "Promoções" to sampleDrinks + sampleCandies,
                "Doces" to sampleCandies,
                "Bebidas" to sampleDrinks
        ),
        onSearchChange = {
            uiState = uiState.copy(searchText = it, searchdProducts = searchedProduct(it))
            }
        ))
        private set


    private fun containsInNameOrDescription(text: String): (Product) -> Boolean = { product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) || product.description?.contains(
            text,
            ignoreCase = true
        ) ?: false
    }

    private fun searchedProduct(text: String): List<Product> =
        if(text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(text)) + dao.products().filter(containsInNameOrDescription(text))
        } else emptyList()

}