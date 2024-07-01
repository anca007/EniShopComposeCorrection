package com.example.enishopcomposecorrection.repository

import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.dao.ArticleDAO
import com.example.enishopcomposecorrection.dao.DaoFactory
import com.example.enishopcomposecorrection.dao.DaoType


class ArticleRepository() {

    private val articleDAO : ArticleDAO = DaoFactory.createArticleDao(DaoType.MEMORY)

    fun getArticle(id : Long) : Article? {
        return articleDAO.findById(id)
    }

    fun addArticle(article: Article) : Long {
        return articleDAO.insert(article)
    }


}