package com.example.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.aluvery.ui.theme.AluveryTheme

class ProductFormActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    Text(text= "teste de texto")
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    
}