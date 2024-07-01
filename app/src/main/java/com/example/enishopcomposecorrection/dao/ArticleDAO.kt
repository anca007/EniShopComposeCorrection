package com.example.enishopcomposecorrection.dao

import com.example.enishopcomposecorrection.bo.Article


interface ArticleDAO {


    fun insert(article: Article): Long


    fun findById(id: Long): Article?

}