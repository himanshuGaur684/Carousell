package com.article_feature.data.repository

import com.article_feature.data.mappers.toDomain
import com.article_feature.domain.model.Article
import com.article_feature.domain.repository.ArticleRepository
import com.core.common.Resource
import com.core_network.data.data_provider.ArticleProvider
import com.core_network.data.model.ArticleDTO

class ArticleRepoImpl(private val dataProvider: ArticleProvider) : ArticleRepository {
    override suspend fun getArticles(): Resource<List<Article>> {
       return try {
            Resource.Success(dataProvider.getArticles().toDomain())
        }catch (e:Exception){
            Resource.Error(message = "Something went wrong")
        }
    }
}