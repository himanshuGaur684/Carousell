package com.article_feature.ui.di

import com.article_feature.domain.di.DomainModule
import com.article_feature.domain.repository.ArticleRepository
import com.article_feature.domain.use_cases.GetArticleUseCase
import com.article_feature.ui.repository.FakeArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UiModule::class, DomainModule::class]
)
@Module
object UiTestModule {

    @Provides
    fun provideArticleRepository(): ArticleRepository {
        return FakeArticleRepositoryImpl()
    }

    @Provides
    fun provideGetArticleUseCase(articleRepository: ArticleRepository): GetArticleUseCase {
        return GetArticleUseCase(articleRepository)
    }

}