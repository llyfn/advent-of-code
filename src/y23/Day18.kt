package y23

import solve
import kotlin.math.abs

fun main() {
    val directions = listOf('R', 'D', 'L', 'U')
    val diffByDir = directions.zip(listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)).toMap()

    fun List<String>.parse(useHex: Boolean): List<Pair<Char, Long>> = map { line ->
        line.split(" ").let { (dir, len, hex) ->
            if (!useHex) dir.first() to len.toLong()
            else hex.trim('(', ')', '#').let { directions[it.last().digitToInt()] to it.dropLast(1).toLong(16) }
        }
    }

    fun getArea(input: List<String>, useHex: Boolean): Long {
        var totalLength = 0L
        val coordinates = buildList {
            var (cRow, cCol) = 0L to 0L
            input.parse(useHex).forEach { (dir, len) ->
                val (dRow, dCol) = diffByDir.getValue(dir)
                cRow += dRow * len
                cCol += dCol * len
                totalLength += len
                add(cRow to cCol)
            }
        }
        val s = coordinates.zipWithNext().sumOf { (p1, p2) -> p1.first * p2.second - p1.second * p2.first }
        return (abs(s) + totalLength) / 2 + 1
    }

    fun part1(input: List<String>): Long = getArea(input, useHex = false)

    fun part2(input: List<String>): Long = getArea(input, useHex = true)

    solve(23, 18, ::part1, ::part2, 62L, 952408144115L)
}
