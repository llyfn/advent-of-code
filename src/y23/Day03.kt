package y23

import findNumbers
import getOrNull
import solve

fun main() {
    fun Char.isSymbol() = !isLetterOrDigit() && !equals('.')

    fun getSurroundingCoordinates(row: Int, colRange: IntRange): List<Pair<Int, Int>> =
        colRange.flatMap { col ->
            (row - 1..row + 1)
                .flatMap { r -> (col - 1..col + 1).map { c -> r to c } }
                .filter { it != row to col }
        }.distinct()

    fun part1(input: List<String>): Int = input.flatMapIndexed() { row, line ->
        line.findNumbers().mapNotNull { match ->
            match.value.toInt().takeIf {
                getSurroundingCoordinates(row, match.range).any { (r, c) ->
                    input.getOrNull(r, c)?.isSymbol() == true
                }
            }
        }
    }.sum()

    fun part2(input: List<String>): Int {
        val adjNumsByGears = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
        input.flatMapIndexed { row, line ->
            line.findNumbers().map { match ->
                getSurroundingCoordinates(row, match.range).forEach { (r, c) ->
                    if (input.getOrNull(r, c) == '*') {
                        adjNumsByGears.getOrPut(r to c) { mutableListOf() }.add(match.value.toInt())
                    }
                }
            }
        }
        return adjNumsByGears.filterValues { it.size == 2 }.map { it.value[0] * it.value[1] }.sum()
    }

    solve(23, 3, ::part1, ::part2, 4361, 467835)
}
