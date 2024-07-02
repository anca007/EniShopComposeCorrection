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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class ArticleFormViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String>
        get() = _name
    //équivalent de foncionnalités
    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()
    private val _price = MutableStateFlow("")
    val price = _price.asStateFlow()
    private val _category = MutableStateFlow("")
    val category = _category.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    fun setName(name: String) {
        _name.value = name
    }

    fun setDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun setPrice(newPrice: String) {
        _price.value = newPrice
    }

    fun setCategory(newCategory: String) {
        _category.value = newCategory
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = articleRepository.getCategories()
        }
    }

    fun addArticle() {
        val newArticle = Article(
            name = name.value,
            description = description.value,
            price = price.value.toFloat(),
            date = Date(),
            category = category.value
        )

        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.addArticle(newArticle)
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

                return ArticleFormViewModel(
                    ArticleRepository(
                        AppDatabase.getInstance(application.applicationContext).articleDAO(),
                        ArticleServiceAPI.ArticleApi.retrofitService
                    )
                ) as T
            }

        }
    }


}