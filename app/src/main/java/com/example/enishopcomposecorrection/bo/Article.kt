package com.example.enishopcomposecorrection.bo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date


@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @Json(name = "title")
    var name: String = "",
    var description: String = "",
    var price: Float = 0f,
    @Json(ignore = true)
    var date: Date = Date(),
    @Json(name = "image")
    var urlImage: String = "",
    var category : String = "",
)




