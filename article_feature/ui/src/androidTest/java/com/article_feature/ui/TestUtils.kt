package com.article_feature.ui

import com.article_feature.domain.model.Article
import com.core.common.getYesterdayTimeEpoch

fun getFakeArticles(): List<Article> {
    return listOf<Article>(
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
        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 1",
            id = "4",
            rank = 34,
            time_created = 12345678,
            title = "Title 4"
        ),
        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 1",
            id = "5",
            rank = 33,
            time_created = 12345654,
            title = "Title 5"
        ),

        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 1",
            id = "6",
            rank = 33,
            time_created = 12345614,
            title = "Title 6"
        ),
        Article(
            banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            description = "Description 1",
            id = "1",
            rank = 89,
            time_created = getYesterdayTimeEpoch(),
            title = "Title 1"
        ),
    )
}