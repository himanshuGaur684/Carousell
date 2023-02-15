package com.article_feature.domain.repository

import com.article_feature.domain.model.Article
import com.core.common.Resource

interface ArticleRepository {

    suspend fun getArticles(): Resource<List<Article>>

}