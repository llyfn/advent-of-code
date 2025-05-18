package y24

import getInts
import product
import solve

fun main() {
    fun part1(input: List<String>): Int =
        Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
            .findAll(input.joinToString(""))
            .map { it.value.getInts().product() }
            .sum()

    fun part2(input: List<String>): Int {
        val s = input.joinToString("").split("don't()")
        return Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
            .findAll(s[0] + s.drop(1).joinToString("") { it.substringAfter("do()", "") })
            .map { it.value.getInts().product() }
            .sum()
    }

    solve(24, 3, ::part1, ::part2, 161, 48)
}