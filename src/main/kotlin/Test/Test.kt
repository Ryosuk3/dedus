package Test

import NumberFormat
import wordsToNumber

fun main() {
    val testCases = listOf(
        // Корректные числа
        "один" to "1",
        "десять" to "10",
        "двадцать три" to "23",
        "сто двадцать пять" to "125",
        "пятьсот шестьдесят семь" to "567",
        "девятьсот девяносто девять" to "999",

        // Некорректный порядок слов
        "пять сто" to "Ошибка: числа сотенного формата не могут идти после чисел единичного формата",
        "двадцать сто" to "Ошибка: числа сотенного формата не могут идти после чисел десяткового формата",
        "один десять" to "Ошибка: числа десяткового формата не могут идти после чисел единичного формата",
        "одиннадцать двадцать" to "Ошибка: числа десяткового формата не могут идти после чисел формата 10-19",

        // Нераспознанные слова
        "тысяча" to "Ошибка: слово 'тысяча' не распознано как часть числа.",
        "две сотни" to "Ошибка: слово 'две' не распознано как часть числа.",

        // Пустой ввод
        "" to "Ошибка: слово '' не распознано как часть числа.",

        // Лишние пробелы
        "  сто   двадцать пять " to "125"
    )

    for ((input, expected) in testCases) {
        val result = wordsToNumber(input)
        println("Input: \"$input\", Expected: \"$expected\", Result: \"$result\", Test: ${if (result == expected) "PASSED" else "FAILED"}")
    }
}