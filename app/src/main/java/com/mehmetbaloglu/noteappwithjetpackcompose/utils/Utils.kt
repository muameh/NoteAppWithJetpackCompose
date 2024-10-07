package com.mehmetbaloglu.noteappwithjetpackcompose.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {


    fun getCurrentDateTime(): String {
        val current = Date()  // Şu anki tarihi alır
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())  // Format belirleme
        return formatter.format(current)  // Formatlanmış tarihi döndürür
    }


}