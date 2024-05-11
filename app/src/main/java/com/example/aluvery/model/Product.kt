package com.example.aluvery.model

import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val description: String? = null
)
