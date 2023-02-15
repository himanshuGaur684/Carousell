package com.article_feature.ui.screens.article_list

import com.article_feature.domain.model.Article

data class ArticleStateHolder(
    val isLoading:Boolean=false,
    val data:List<Article>?=null,
    val error:String=""
)
