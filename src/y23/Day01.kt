package y23

import solve

fun main() {
    fun part1(input: List<String>): Int =
        input.sumOf { s -> "${s.first { it.isDigit() }}${s.last { it.isDigit() }}".toInt() }

    fun part2(input: List<String>): Int {
        val digits = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        val letters = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val digitsByLetters = letters.zip(digits).toMap()
        return input.sumOf { s ->
            val firstDigit = s.findAnyOf(digits + letters)!!.second.let { digitsByLetters[it] ?: it }
            val lastDigit = s.findLastAnyOf(digits + letters)!!.second.let { digitsByLetters[it] ?: it }
            (firstDigit + lastDigit).toInt()
        }
    }

    solve(23, 1, ::part1, ::part2, 142, 281)
}
