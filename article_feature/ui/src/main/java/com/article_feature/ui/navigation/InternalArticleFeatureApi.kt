package com.article_feature.ui.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.article_feature.ui.screens.article_list.ArticleListScreen
import com.core.common.navigation.ArticleFeatureNavigationRoute
import com.core.common.navigation.ArticleFeatureNavigationRoute.nestedGraphRoute
import com.core.common.navigation.FeatureApi


object InternalArticleFeatureApi : FeatureApi {
    override fun registerNavigation(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = ArticleFeatureNavigationRoute.articleList,
            route = nestedGraphRoute
        ) {
            composable(ArticleFeatureNavigationRoute.articleList) {
                ArticleListScreen(viewModel = hiltViewModel())
            }
        }
    }
}