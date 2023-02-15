package com.core.common.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController


interface FeatureApi {

    fun registerNavigation(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    )

}