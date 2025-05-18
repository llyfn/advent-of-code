package y23

import solve
import kotlin.math.pow

fun main() {
    fun String.getNumbers(): List<List<Int>> =
        substringAfter(": ")
            .split(" | ")
            .map { s -> s.split(" ").mapNotNull { it.toIntOrNull() } }

    fun part1(input: List<String>): Int =
        input.map { it.getNumbers() }.sumOf { (winnings, numbers) ->
            val count = numbers.count { it in winnings }

            2.0.pow(count - 1).toInt()
        }

    fun part2(input: List<String>): Int {
        val copiesByCardId = MutableList(input.size) { 1 }

        input.map { line ->
            line.getNumbers().let { (winnings, numbers) -> numbers.count { it in winnings } }
        }.forEachIndexed { idx, match ->
            repeat(match) { copiesByCardId[idx + it + 1] += copiesByCardId[idx] }
        }

        return copiesByCardId.sum()
    }

    solve(23, 4, ::part1, ::part2, 13, 30)
}
