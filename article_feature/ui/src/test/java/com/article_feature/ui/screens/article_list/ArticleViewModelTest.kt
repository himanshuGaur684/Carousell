package com.article_feature.ui.screens.article_list

import com.article_feature.domain.model.Article
import com.article_feature.domain.use_cases.GetArticleUseCase
import com.article_feature.ui.MainCoroutineRule
import com.article_feature.ui.getFakeArticles
import com.article_feature.ui.screens.article_list.utils.SortingOption
import com.core.common.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getArticleUseCase = mock<GetArticleUseCase>()
    private lateinit var articleViewModel: ArticleViewModel

    private val data = mock<List<Article>>()

    @Before
    fun setUp() {
        articleViewModel = ArticleViewModel(getArticleUseCase)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun `invoke getArticleUseCase initially`() = runTest {
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        verify(getArticleUseCase, times(1)).invoke()
    }

    @Test
    fun `first emission from getArticleUseCase is Resource_Loading()`() = runTest {
        whenever(getArticleUseCase()).thenReturn(
            flow {
                emit(Resource.Loading())
            }
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(true, articleViewModel.articleList.value.isLoading)
    }

    @Test
    fun `when response successful update articleList`() = runTest {
        whenever(getArticleUseCase()).thenReturn(
            flow {
                emit(Resource.Success(data = data))
            }
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(data, articleViewModel.articleList.value.data)
    }

    @Test
    fun `when error occurred in getArticleUseCase`() = runTest {
        val errorMessage = "Something went wrong"
        whenever(getArticleUseCase()).thenReturn(
            flow {
                emit(Resource.Error(message = errorMessage))
            }
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(errorMessage, articleViewModel.articleList.value.error)
    }

    @Test
    fun `initial value of sorting variable is Sorting_NOTHING`()= runTest {
        Assert.assertEquals(SortingOption.NOTHING,articleViewModel.sortingOption.value)
    }


    @Test
    fun `popular sorting`() = runTest {
        val articleList = getFakeArticles()
        whenever(getArticleUseCase()).thenReturn(
            flow {
                emit(Resource.Success(data = articleList))
            }
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle() // finish all coroutine and update articleList
        articleViewModel.sortArticles(SortingOption.POPULAR)
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle() // finish all coroutines for sorting and update articleList
        val sortedArticles = getFakeArticles().sortedWith(compareBy(Article::rank,Article::time_created)).reversed()
        Assert.assertEquals(sortedArticles,articleViewModel.articleList.value.data)
    }

    @Test
    fun `recent sorting`()= runTest {
        val articleList = getFakeArticles()
        whenever(getArticleUseCase()).thenReturn(
            flow {
                emit(Resource.Success(data = articleList))
            }
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle() // finish all coroutine and update articleList
        articleViewModel.sortArticles(SortingOption.RECENT)
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle() // finish all coroutines for sorting and update articleList
        val sortedArticles = getFakeArticles().sortedByDescending { it.time_created }
        Assert.assertEquals(sortedArticles,articleViewModel.articleList.value.data)
    }

}