package com.example.enishopcomposecorrection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.enishopcomposecorrection.service.DataStoreManager
import com.example.enishopcomposecorrection.ui.screen.ArticleDetailScreen
import com.example.enishopcomposecorrection.ui.screen.ArticleFormScreen
import com.example.enishopcomposecorrection.ui.screen.ArticleListScreen
import com.example.enishopcomposecorrection.ui.theme.EniShopComposeCorrectionTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = this;

        setContent {

            var isDarkThemeActivated by rememberSaveable {
                mutableStateOf(false)
            }

            LaunchedEffect(Unit) {
                DataStoreManager.isDarkThemeActivited(activity).collect {
                    isDarkThemeActivated = it
                }
            }

            EniShopComposeCorrectionTheme(darkTheme = isDarkThemeActivated) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    EniShopApp(
                        isDarkThemeActivated = isDarkThemeActivated,
                        onDarkThemeToggle = { isActivated: Boolean ->
                            lifecycleScope.launch {
                                DataStoreManager.setDarkThemeActivated(activity, isActivated)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EniShopApp(
    modifier: Modifier = Modifier,
    isDarkThemeActivated: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit
) {

    val navController = rememberNavController()
    EniShopNavHost(
        navController = navController,
        isDarkThemeActivated = isDarkThemeActivated,
        onDarkThemeToggle = onDarkThemeToggle
    )
}

@Composable
fun EniShopNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isDarkThemeActivated: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit
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
                navController = navController,
                isDarkThemeActivated = isDarkThemeActivated,
                onDarkThemeToggle = onDarkThemeToggle
            )
        }
        this.composable(EniShopAdd.route) {
            ArticleFormScreen(
                navController = navController,
                isDarkThemeActivated = isDarkThemeActivated,
                onDarkThemeToggle = onDarkThemeToggle
            )
        }
        this.composable(
            route = EniShopDetail.routeWithArgs,
            arguments = EniShopDetail.arguments
        ) {
            val articleId = it.arguments?.getLong(EniShopDetail.articleDetailArg) ?: 0
            ArticleDetailScreen(
                articleId = articleId,
                navController = navController,
                isDarkThemeActivated = isDarkThemeActivated,
                onDarkThemeToggle = onDarkThemeToggle
            )
        }
    }
}





