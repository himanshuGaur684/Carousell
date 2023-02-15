package com.article_feature.data.di

import com.article_feature.data.repository.ArticleRepoImpl
import com.article_feature.domain.repository.ArticleRepository
import com.core_network.data.data_provider.ArticleProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    fun provideArticleRepo(dataProvider: ArticleProvider):ArticleRepository{
        return ArticleRepoImpl(dataProvider)
    }

}