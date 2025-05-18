package y23

import solve
import kotlin.math.absoluteValue

fun main() {
    fun List<String>.getSumOfPaths(factor: Int): Long {
        val (n, m) = size to get(0).length
        val galaxies = flatMapIndexed { row, line ->
            line.mapIndexedNotNull { col, char -> (row to col).takeIf { char == '#' } }
        }
        val (emptyRows, emptyCols) = galaxies.unzip().let { (rows, cols) ->
            (0..<n).filterNot { it in rows } to (0..<m).filterNot { it in cols }
        }
        val galaxiesAfterExpansion = galaxies.map { (row, col) ->
            row + (0..row).count { it in emptyRows } * (factor - 1) to
                    col + (0..col).count { it in emptyCols } * (factor - 1)
        }
        return galaxiesAfterExpansion.withIndex().sumOf { (idx, it) ->
            galaxiesAfterExpansion.drop(idx + 1).sumOf { (row, col) ->
                ((it.first - row).absoluteValue + (it.second - col).absoluteValue).toLong()
            }
        }
    }

    fun part1(input: List<String>): Long = input.getSumOfPaths(2)
    fun part2(input: List<String>): Long = input.getSumOfPaths(1000000)

    solve(23, 11, ::part1, ::part2, 374L)
}
