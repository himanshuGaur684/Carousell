package com.example.carousell.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation.ArticleFeatureNavigationRoute


@Composable
fun AppNavGraph(
    navController: NavHostController,
    navGraProvider: NavigationProvider,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ArticleFeatureNavigationRoute.nestedGraphRoute
    ) {
        navGraProvider.articleFeatureApi.registerNavigation(
            navController, this, modifier
        )
    }
}