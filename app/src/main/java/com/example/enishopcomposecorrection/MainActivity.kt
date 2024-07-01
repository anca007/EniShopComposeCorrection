package com.example.enishopcomposecorrection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.enishopcomposecorrection.ui.screen.ArticleDetailScreen
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
                    EniShopApp()
                }
            }
        }
    }
}

@Composable
fun EniShopApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    EniShopNavHost(navController = navController)
}

@Composable
fun EniShopNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = EniShopHome.route,
        modifier = modifier
    ) {
        this.composable(
            route = EniShopHome.route,
        ) {
            ArticleListScreen(
                onClickOnArticleItem = {
                    navController.navigate("${EniShopDetail.route}/$it")
                },
                navController = navController
            )
        }
        this.composable(EniShopAdd.route) {
            ArticleFormScreen(navController = navController)
        }
        this.composable(
            route = EniShopDetail.routeWithArgs,
            arguments = EniShopDetail.arguments
        ) {
            val articleId = it.arguments?.getLong(EniShopDetail.articleDetailArg) ?: 0
            ArticleDetailScreen(
                articleId = articleId,
                navController = navController
            )
        }
    }
}





