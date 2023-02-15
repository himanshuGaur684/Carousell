package com.article_feature.data.repository

import com.article_feature.data.getFakeArticles
import com.article_feature.data.mappers.toDomain
import com.article_feature.domain.repository.ArticleRepository
import com.core_network.data.ApiService
import com.core_network.data.data_provider.ArticleProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.*

class ArticleRepositoryTest {

    private val articleProvider: ArticleProvider = mock<ArticleProvider>()

    private lateinit var articleRepository: ArticleRepository
    @Before
    fun setUp() {
        articleRepository = ArticleRepoImpl(articleProvider)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when articleProvider return articles successfully`()= runTest {
    val fakeArticlesDTO = getFakeArticles()
        whenever(articleProvider.getArticles()).thenReturn(fakeArticlesDTO)
        val articles = articleRepository.getArticles().data
        Assert.assertEquals(true, getFakeArticles().toDomain() == articles)
    }

    @Test
    fun `when articleProvider return error`()= runTest {
        val error = "Something went wrong"
        whenever(articleProvider.getArticles()).thenThrow(java.lang.RuntimeException(error))
        val result = articleRepository.getArticles().message
        Assert.assertEquals(error,result)
    }

}