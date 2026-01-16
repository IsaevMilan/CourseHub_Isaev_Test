package com.example.domain.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val russianLocale = Locale("ru", "RU")

private val inputFormatters = listOf(
    DateTimeFormatter.ISO_LOCAL_DATE,
    DateTimeFormatter.ofPattern("yyyy-MM-dd")
)

fun String.toLocalDateOrNull(): LocalDate? {
    inputFormatters.forEach { formatter ->
        try {
            return LocalDate.parse(this, formatter)
        } catch (e: Exception) {

        }
    }
    return null
}

// форматирование
fun String.formatRussianDate(
    pattern: String = "d MMMM YYYY"

): String {
    val date = this.toLocalDateOrNull() ?: return this

    val formatter = DateTimeFormatter
        .ofPattern(pattern, russianLocale)

    return date.format(formatter)
}