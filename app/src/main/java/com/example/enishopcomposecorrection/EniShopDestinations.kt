package com.example.enishopcomposecorrection

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface EniShopDestination {
    val icon: ImageVector
    val route: String
}

object EniShopHome : EniShopDestination {
    override val icon = Icons.Default.Home
    override val route = "home"
}

object EniShopAdd : EniShopDestination {
    override val icon = Icons.Default.Add
    override val route = "article_add"
}

object EniShopDetail : EniShopDestination {
    override val icon = Icons.Default.Dashboard
    override val route = "article_detail"

    val articleDetailArg = "article_id"
    val arguments = listOf(
        navArgument(articleDetailArg) { type = NavType.LongType }
    )
    val routeWithArgs = "${route}/{${articleDetailArg}}"
}