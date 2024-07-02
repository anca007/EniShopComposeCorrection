package com.example.enishopcomposecorrection.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.dao.DaoType
import com.example.enishopcomposecorrection.db.AppDatabase
import com.example.enishopcomposecorrection.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _article = MutableStateFlow<Article>(Article())
    val article: StateFlow<Article>
        get() = _article

    private val _checkedFav = MutableStateFlow(false)
    val checkedFav: StateFlow<Boolean>
        get() = _checkedFav


    fun initArticle(id: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            val a = articleRepository.getArticle(id, DaoType.ROOM)
            if (a != null) {
                _checkedFav.value = true
            }
        }

        val currentArticle = articleRepository.getArticle(id)
        if (currentArticle != null) {
            _article.value = currentArticle
        }
    }

    fun updateCheckBox(){
        _checkedFav.value = !_checkedFav.value
    }

    fun addArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.addArticle(article.value, DaoType.ROOM)
        }
    }

    fun deleteArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.deleteArticle(article.value, DaoType.ROOM)
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
                    ArticleRepository(
                        AppDatabase.getInstance(application.applicationContext).articleDAO()
                    )
                ) as T
            }

        }
    }

}