package com.article_feature.ui.screens.article_list

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import com.article_feature.domain.model.Article
import com.article_feature.domain.use_cases.GetArticleUseCase
import com.article_feature.ui.getFakeArticles
import com.article_feature.ui.screens.article_list.utils.SortingOption
import com.article_feature.ui.screens.article_list.utils.TestTag
import com.core.common.differenceOfDateFromCurrentDate
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
class ArticleListScreenKtTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createComposeRule()

    @Inject
    lateinit var getArticleUseCase: GetArticleUseCase

    lateinit var context: Context

    lateinit var viewModel: ArticleViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = ArticleViewModel(getArticleUseCase)
        context = ApplicationProvider.getApplicationContext()

        composeRule.setContent {
            ArticleListScreen(viewModel = viewModel)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun appBarTitleIsVisible() {
        composeRule.apply {
            onNodeWithText(context.getString(com.article_feature.ui.R.string.app_title)).assertIsDisplayed()
        }
    }

    @Test
    fun listVisibleOnSuccessResponseFromServer() {
        composeRule.apply {
            onNodeWithTag(TestTag.ARTICLE_LIST).assertIsDisplayed()
        }
    }

    @Test
    fun checkPopularSortingFunctionality() {
        composeRule.apply {
            onNodeWithTag(TestTag.ARTICLE_LIST).assertIsDisplayed()
            onNodeWithTag(TestTag.ARTICLE_SCREEN_MORE_VERT).performClick()
            onNodeWithText(SortingOption.POPULAR.sortingName).performClick()
            val firstRankTitle = getFakeArticles().sortedWith(compareBy(Article::rank,Article::time_created)).reversed()[0].title
            onNodeWithText(firstRankTitle).assertIsDisplayed()
        }
    }

    @Test
    fun checkRecentSortingFunctionality() {
        composeRule.apply {
            onNodeWithTag(TestTag.ARTICLE_LIST).assertIsDisplayed()
            onNodeWithTag(TestTag.ARTICLE_SCREEN_MORE_VERT).performClick()
            onNodeWithText(SortingOption.RECENT.sortingName).performClick()
            val firstListItemTimeEpoch = getFakeArticles().sortedByDescending { it.time_created }[0].time_created
            val formattedTimeOfFirstListItem = differenceOfDateFromCurrentDate(firstListItemTimeEpoch)
            onNodeWithText(formattedTimeOfFirstListItem).assertIsDisplayed()
        }
    }


}