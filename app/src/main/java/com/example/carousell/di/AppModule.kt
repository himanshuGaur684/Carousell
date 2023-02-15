package com.example.carousell.di

import com.article_feature.ui.navigation.ArticleFeatureApi
import com.example.carousell.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvider(articleFeatureApi: ArticleFeatureApi): NavigationProvider {
        return NavigationProvider(articleFeatureApi)
    }

}