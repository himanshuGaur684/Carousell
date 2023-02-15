package com.article_feature.domain.di

import com.article_feature.domain.repository.ArticleRepository
import com.article_feature.domain.use_cases.GetArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetArticleUseCase(articleRepository: ArticleRepository):GetArticleUseCase{
        return GetArticleUseCase(articleRepository)
    }

}