package com.article_feature.ui.repository

import com.article_feature.domain.model.Article
import com.article_feature.domain.repository.ArticleRepository
import com.article_feature.ui.getFakeArticles
import com.core.common.Resource

class FakeArticleRepositoryImpl : ArticleRepository {
    override suspend fun getArticles(): Resource<List<Article>> = Resource.Success(getFakeArticles())
}