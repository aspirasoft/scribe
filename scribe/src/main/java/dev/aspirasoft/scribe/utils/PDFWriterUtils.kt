package dev.aspirasoft.scribe.utils

import java.util.*


internal fun mmToInches(mm: Float): Float {
    return mm / 25.4F
}

internal fun mmToPixels(mm: Float, dpi: Int = 72): Float {
    return dpi * mmToInches(mm)
}

internal fun String.toTitleCase(): String {
    val ignore = arrayOf(
        "a",
        "an",
        "and",
        "as",
        "at",
        "but",
        "by",
        "for",
        "from",
        "if",
        "in",
        "into",
        "nor",
        "of",
        "off",
        "on",
        "once",
        "onto",
        "or",
        "over",
        "past",
        "so",
        "than",
        "that",
        "the",
        "till",
        "to",
        "up",
        "upon",
        "with",
        "when",
        "yet"
    )
    val dividers = arrayOf('.', ':', '!', '?')

    var last: String? = null
    val titled = StringBuilder()
    this.split(' ')
        .map { word ->
            val capitalized = when {
                last == null -> word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                ignore.contains(word.lowercase(Locale.getDefault())) -> word
                dividers.any {
                    it == kotlin.runCatching { last?.last() }.getOrNull()
                } -> word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                else -> word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            }
            last = word
            capitalized
        }
        .forEach { titled.append("$it ") }
    return titled.toString()
}