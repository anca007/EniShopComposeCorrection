package com.example.enishopcomposecorrection.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.db.AppDatabase
import com.example.enishopcomposecorrection.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleListViewModel(articleRepository: ArticleRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>>
        get() = _articles

    init {
        _categories.value = listOf("electronics", "jewelery", "men's clothing", "women's clothing")
        _articles.value = articleRepository.getAllArticles()
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

                return ArticleListViewModel(
                    ArticleRepository(AppDatabase.getInstance(application.applicationContext).articleDAO())
                ) as T
            }

        }
    }

}