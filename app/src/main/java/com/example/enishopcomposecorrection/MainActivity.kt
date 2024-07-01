package com.example.enishopcomposecorrection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.enishopcomposecorrection.repository.ArticleRepository
import com.example.enishopcomposecorrection.ui.screen.ArticleFormScreen
import com.example.enishopcomposecorrection.ui.screen.ArticleListScreen
import com.example.enishopcomposecorrection.ui.theme.EniShopComposeCorrectionTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EniShopComposeCorrectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ArticleListScreen()
                }
            }
        }
    }
}



