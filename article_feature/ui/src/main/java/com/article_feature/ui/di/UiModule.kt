package com.article_feature.ui.di

import com.article_feature.domain.repository.ArticleRepository
import com.article_feature.domain.use_cases.GetArticleUseCase
import com.article_feature.ui.navigation.ArticleFeatureApi
import com.article_feature.ui.navigation.ArticleFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideArticleFeature():ArticleFeatureApi{
        return ArticleFeatureApiImpl()
    }

}