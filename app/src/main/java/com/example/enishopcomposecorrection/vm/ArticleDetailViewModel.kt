package com.example.enishopcomposecorrection.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleDetailViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _article = MutableStateFlow<Article>(Article())
    val article: StateFlow<Article>
        get() = _article

    fun initArticle(id: Long) {

        val currentArticle = articleRepository.getArticle(id)
        if (currentArticle != null) {
            _article.value = currentArticle
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return ArticleDetailViewModel(
                    ArticleRepository()
                ) as T
            }

        }
    }

}