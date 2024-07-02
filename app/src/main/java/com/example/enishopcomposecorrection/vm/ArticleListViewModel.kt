package com.example.enishopcomposecorrection.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.dao.network.ArticleServiceAPI
import com.example.enishopcomposecorrection.db.AppDatabase
import com.example.enishopcomposecorrection.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleListViewModel(articleRepository: ArticleRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>>
        get() = _articles

    var isLoading = MutableStateFlow(true)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            //montrer que comme cela, c'est lancé de manière séquentiel
            //_articles.value = articleRepository.getAllArticles()
            //_categories.value = articleRepository.getCategories()
            //voir pour ajouter le loading page

            val a = async { _articles.value = articleRepository.getAllArticles() }
            val c = async {_categories.value = articleRepository.getCategories() }
            awaitAll(a, c)
            isLoading.value = false
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

                return ArticleListViewModel(
                    ArticleRepository(
                        AppDatabase.getInstance(application.applicationContext).articleDAO(),
                        ArticleServiceAPI.ArticleApi.retrofitService
                    )
                ) as T
            }

        }
    }

}