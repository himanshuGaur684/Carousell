package com.article_feature.domain.use_cases

import com.article_feature.domain.model.Article
import com.article_feature.domain.repository.ArticleRepository
import com.core.common.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class GetArticleUseCaseTest {

    private lateinit var getArticleUseCase: GetArticleUseCase
    private var articleRepository = mock<ArticleRepository>()

    private val articleList = mock<List<Article>>()

    @Before
    fun setUp() {
        getArticleUseCase = GetArticleUseCase(articleRepository)
    }

    @Test
    fun `initially emit loading`()= runTest {
        val loading = getArticleUseCase.invoke()
        val resource = loading.first()
        Assert.assertEquals(true,resource is Resource.Loading)
    }

    @Test
    fun `when response is successful`() = runTest {
        whenever(articleRepository.getArticles()).thenReturn(Resource.Success(articleList))
        val resource=getArticleUseCase.invoke()
        val dataFromUseCase = resource.last().data
        Assert.assertEquals(articleList,dataFromUseCase)
    }

    @Test
    fun `when error occurred during network call`() = runTest {
        val error= "Something went wrong"
        whenever(articleRepository.getArticles()).thenReturn(Resource.Error(message = error))
        val resource = getArticleUseCase.invoke()
        val errorMessage = resource.last().message
        Assert.assertEquals(error,errorMessage)
    }

}