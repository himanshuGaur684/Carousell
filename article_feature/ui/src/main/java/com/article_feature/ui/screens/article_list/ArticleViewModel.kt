package com.article_feature.ui.screens.article_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.article_feature.domain.model.Article
import com.article_feature.domain.use_cases.GetArticleUseCase
import com.article_feature.ui.screens.article_list.utils.SortingOption
import com.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val getArticleUseCase: GetArticleUseCase) :
    ViewModel() {


    private val _sortingOption = mutableStateOf<SortingOption>(SortingOption.NOTHING)
    val sortingOption: State<SortingOption> get() = _sortingOption

    private fun setSortingOption(sortingOption: SortingOption) {
        _sortingOption.value = sortingOption
    }

    private val _articleList = mutableStateOf(ArticleStateHolder())
    val articleList: State<ArticleStateHolder> get() = _articleList

    init {
        getArticles()
    }

    fun sortArticles(sortingOption: SortingOption) = viewModelScope.launch {
        setSortingOption(sortingOption)
        _articleList.value.data?.let { articles ->
            val sortingArticles = when (sortingOption) {
                SortingOption.POPULAR -> {
                    articles.sortedWith(compareBy(Article::rank,Article::time_created)).reversed()
                }
                SortingOption.RECENT -> {
                    articles.sortedByDescending { it.time_created }
                }
                SortingOption.NOTHING->{
                    emptyList()
                }
            }
            _articleList.value = ArticleStateHolder(data = sortingArticles)
        }
    }

    private fun getArticles() = viewModelScope.launch {
        getArticleUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _articleList.value = ArticleStateHolder(isLoading = true)
                }
                is Resource.Error -> {
                    _articleList.value = ArticleStateHolder(error = it.message.toString())
                }
                is Resource.Success -> {
                    _articleList.value = ArticleStateHolder(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}