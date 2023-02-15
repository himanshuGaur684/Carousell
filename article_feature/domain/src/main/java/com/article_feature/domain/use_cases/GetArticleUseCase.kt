package com.article_feature.domain.use_cases

import com.article_feature.domain.model.Article
import com.article_feature.domain.repository.ArticleRepository
import com.core.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetArticleUseCase constructor(private val articleRepository: ArticleRepository) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow<Resource<List<Article>>> {
        emit(Resource.Loading())
        when(val result = articleRepository.getArticles()){
            is Resource.Success->{
                result.data?.let {
                    emit(Resource.Success(it))
                }
            }
            is Resource.Error->{
                emit(Resource.Error(result.message.toString()))
            }
            else->{}
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}