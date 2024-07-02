package com.example.enishopcomposecorrection.repository

import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.dao.ArticleDAO
import com.example.enishopcomposecorrection.dao.DaoFactory
import com.example.enishopcomposecorrection.dao.DaoType


class ArticleRepository(
    private val articleDAORoomImpl: ArticleDAO
) {

    private val articleDAOMemoryImpl  = DaoFactory.createArticleDao(DaoType.MEMORY)

    fun getArticle(id : Long, daoType: DaoType = DaoType.MEMORY) : Article? {
        return when(daoType){
            DaoType.MEMORY -> articleDAOMemoryImpl.findById(id)
            else -> articleDAORoomImpl.findById(id)
        }

    }

    fun getAllArticles(daoType: DaoType = DaoType.MEMORY): List<Article> {
        return when(daoType){
            DaoType.MEMORY -> articleDAOMemoryImpl.findAll()
            else -> articleDAORoomImpl.findAll()
        }

    }

    fun addArticle(article: Article, daoType: DaoType = DaoType.MEMORY) : Long {
        return when(daoType){
            DaoType.MEMORY -> articleDAOMemoryImpl.insert(article)
            else -> articleDAORoomImpl.insert(article)
        }
    }

    fun deleteArticle(article: Article, daoType: DaoType = DaoType.MEMORY){
        return when(daoType){
            DaoType.MEMORY -> articleDAOMemoryImpl.delete(article)
            else -> articleDAORoomImpl.delete(article)
        }
    }


}