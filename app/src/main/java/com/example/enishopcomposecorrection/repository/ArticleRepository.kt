package com.example.enishopcomposecorrection.repository

import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.dao.ArticleDAO
import com.example.enishopcomposecorrection.dao.DaoFactory
import com.example.enishopcomposecorrection.dao.DaoType
import com.example.enishopcomposecorrection.dao.network.ArticleServiceAPI


class ArticleRepository(
    private val articleDAORoomImpl: ArticleDAO,
    private val articleAPI: ArticleServiceAPI
) {

    //private val articleDAOMemoryImpl  = DaoFactory.createArticleDao(DaoType.MEMORY)

    suspend fun getCategories(): List<String> {
        return articleAPI.getCategories()
    }

    suspend fun getArticle(id : Long, daoType: DaoType = DaoType.NETWORK) : Article? {
        return when(daoType){
            DaoType.NETWORK -> articleAPI.getArticleById(id)
            else -> articleDAORoomImpl.findById(id)
        }

    }

    suspend fun getAllArticles(daoType: DaoType = DaoType.NETWORK): List<Article> {
        return when(daoType){
            DaoType.NETWORK -> articleAPI.getArticles()
            else -> articleDAORoomImpl.findAll()
        }

    }

    suspend fun addArticle(article: Article, daoType: DaoType = DaoType.NETWORK) {
        when(daoType){
            DaoType.NETWORK -> articleAPI.addArticle(article)
            else -> articleDAORoomImpl.insert(article)
        }
    }

    fun deleteArticle(article: Article, daoType: DaoType = DaoType.NETWORK){
        return when(daoType){
            DaoType.NETWORK -> TODO()
            else -> articleDAORoomImpl.delete(article)
        }
    }


}