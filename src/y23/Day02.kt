package y23

import solve

fun main() {
    fun List<String>.findIntBefore(s: String) = find { it.endsWith(s) }?.substringBefore(s)?.toInt() ?: 0

    fun String.parse(): Pair<Int, List<List<Int>>> {
        val id = substringBefore(":").substringAfter(" ").toInt()
        val subsets = substringAfter(": ").split("; ")
        return id to subsets.map { subset ->
            subset.split(", ").run { listOf(findIntBefore(" red"), findIntBefore(" green"), findIntBefore(" blue")) }
        }
    }

    fun part1(input: List<String>): Int = input.map { it.parse() }
        .filter { (_, subsets) -> subsets.all { it[0] <= 12 && it[1] <= 13 && it[2] <= 14 } }.sumOf { it.first }

    fun part2(input: List<String>): Int = input.map { it.parse() }
        .sumOf { (_, subsets) -> subsets.run { maxOf { it[0] } * maxOf { it[1] } * maxOf { it[2] } } }

    solve(23, 2, ::part1, ::part2, 8, 2286)
}
