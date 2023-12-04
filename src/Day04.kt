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

    check(part1(readInput("Day04_test")) == 13)
    check(part2(readInput("Day04_test")) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
