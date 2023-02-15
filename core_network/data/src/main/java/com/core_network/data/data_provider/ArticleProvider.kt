package com.core_network.data.data_provider

import com.core_network.data.ApiService
import javax.inject.Inject

class ArticleProvider @Inject constructor(private val apiService: ApiService) {

    suspend fun getArticles() = apiService.getArticleList()

}