package com.example.enishopcompose.utils

import java.text.SimpleDateFormat
import java.util.Date



fun Date.convertDateToSimpleString(): String? {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}

//object DateConverter {
//    val formatter = SimpleDateFormat("dd/MM/yyyy")
//
//
//    fun convertDateToSimpleString(date : Date): String {
//        return formatter.format(date)
//    }
//
//
//}