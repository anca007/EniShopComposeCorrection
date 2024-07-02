package com.example.enishopcomposecorrection.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object DateConverter {
    val formatter = SimpleDateFormat("dd/MM/yyyy")

    //@ToJson
    fun convertDateToSimpleString(date : Date): String {
        return formatter.format(date)
    }

    //@FromJson
    fun convertStringToSimpleDate(text : String): Date {
        var date = Date()
        try {
            date = formatter.parse(text)
        }catch (e: ParseException){

        }
        return date
    }


}