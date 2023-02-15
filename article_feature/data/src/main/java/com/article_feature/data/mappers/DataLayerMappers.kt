package com.article_feature.data.mappers

import com.article_feature.domain.model.Article
import com.core_network.data.model.ArticleDTO

fun List<ArticleDTO>.toDomain():List<Article>{
    return map {
        Article(
            banner_url = it.banner_url,
            description = it.description,
            id=it.id,
            rank=it.rank,
            time_created = it.time_created ,
            title = it.title
        )
    }
}