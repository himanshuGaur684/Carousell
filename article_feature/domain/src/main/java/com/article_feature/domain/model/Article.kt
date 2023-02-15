package com.article_feature.domain.model

data class Article(
    val banner_url: String,
    val description: String,
    val id: String,
    val rank: Int,
    val time_created: Int,
    val title: String
)
