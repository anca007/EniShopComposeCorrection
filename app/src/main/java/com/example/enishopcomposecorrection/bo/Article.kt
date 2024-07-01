package com.example.enishopcomposecorrection.bo


import java.util.Date


data class Article(
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var price: Float = 0f,
    var date: Date = Date(),
    var urlImage: String = "",
    var category : String = "",
)




