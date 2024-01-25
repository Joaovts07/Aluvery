package com.example.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.aluvery.dao.ProductDao
import com.example.aluvery.ui.screens.ProductFormScreen
import com.example.aluvery.ui.screens.ProductFormScreenUIState
import com.example.aluvery.ui.theme.AluveryTheme

class ProductFormActivity: ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    val state = ProductFormScreenUIState(onSaveClick = { product ->
                        dao.save(product)
                        finish()
                    })
                    ProductFormScreen(state = state)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductFormScreenPreview() {
    ProductFormScreen()
}
