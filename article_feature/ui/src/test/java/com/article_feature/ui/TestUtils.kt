package com.article_feature.ui

import com.article_feature.domain.model.Article

fun getFakeArticles(): List<Article> {
    return listOf<Article>(
        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 1",
            id = "1",
            rank = 89,
            time_created = 12345678,
            title = "Title 1"
        ),
        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 2",
            id = "2",
            rank = 12,
            time_created = 12345645,
            title = "Title 2"
        ),
        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 1",
            id = "3",
            rank = 43,
            time_created = 12345692,
            title = "Title 3"
        ),
    )
}