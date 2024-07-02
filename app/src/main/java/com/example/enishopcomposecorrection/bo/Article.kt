package com.example.enishopcomposecorrection.bo


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var price: Float = 0f,
    var date: Date = Date(),
    var urlImage: String = "",
    var category : String = "",
)




