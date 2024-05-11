package com.example.aluvery.ui.states

import com.example.aluvery.model.Product

data class ProductFormScreenUIState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val isShowPreview: Boolean = url.isNotBlank(),
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onSaveClick: (Product) -> Unit ={},
) {
    fun isNotBlank(url: String): Boolean = url.isNotBlank()
}