package com.core_network.data

import com.core_network.data.model.ArticleDTO
import retrofit2.http.GET

interface ApiService {

    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun getArticleList():List<ArticleDTO>

}