package com.example.aluvery.ui.viewmodels

import com.example.aluvery.ui.states.ProductFormScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductFormScreenViewModel {

    private val _uiState: MutableStateFlow<ProductFormScreenUIState> = MutableStateFlow(
        ProductFormScreenUIState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },
                onPriceChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                }, onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                }
            )
        }
    }
}