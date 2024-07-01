package com.example.enishopcomposecorrection.dao

import com.example.enishopcomposecorrection.dao.memory.ArticleDAOMemoryImpl

abstract class DaoFactory {
    companion object {
        fun createArticleDao(type: DaoType): ArticleDAO {

            val dao: ArticleDAO

            when (type) {
                DaoType.MEMORY -> dao = ArticleDAOMemoryImpl()
                DaoType.NETWORK -> TODO()
            }

            return dao
        }
    }

}