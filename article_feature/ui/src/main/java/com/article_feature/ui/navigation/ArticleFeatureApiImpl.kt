package com.article_feature.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController


class ArticleFeatureApiImpl : ArticleFeatureApi {

    val baseRoute = "article_feature_route"

    override fun baseRoute(): String = baseRoute

    override fun registerNavigation(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        InternalArticleFeatureApi.registerNavigation(
            navController, navGraphBuilder, modifier
        )
    }
}