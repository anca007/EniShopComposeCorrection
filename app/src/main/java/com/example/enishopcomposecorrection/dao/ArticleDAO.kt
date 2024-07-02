package com.example.enishopcomposecorrection.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.enishopcomposecorrection.bo.Article


@Dao
interface ArticleDAO {

    @Insert
    fun insert(article: Article): Long

    @Query("SELECT * FROM Article WHERE id = :id")
    fun findById(id: Long): Article?

    @Query("SELECT * FROM Article")
    fun findAll() : List<Article>

    @Delete
    fun delete(article: Article)

}