package com.idealista.android.wrench

import com.idealista.android.wrench.type.ApplicativeOption
import com.idealista.android.wrench.type.toOption

fun main(args: Array<String>) {
    val map: Map<String, String> = mapOf("book" to "78",
            "book1" to "7891",
            "book2" to "89000")

    val first = map["book"].toOption()
    val second = map["book1"].toOption()
    val third = map["book2"].toOption()
    val fourth = map["book2"].toOption()

    ApplicativeOption.map(first, second, third, fourth, { (first, second, third, fourth) ->
        "$first $second $third $fourth"
    }).map(::println)

}