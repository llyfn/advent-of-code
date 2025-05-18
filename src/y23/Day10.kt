package y23

import getOrNull
import solve

fun main() {
    val connectionsByPipes = mapOf(
        'S' to listOf(1 to 0, 0 to 1, 0 to -1, -1 to 0),
        '|' to listOf(1 to 0, -1 to 0),
        '-' to listOf(0 to 1, 0 to -1),
        'L' to listOf(-1 to 0, 0 to 1),
        'J' to listOf(-1 to 0, 0 to -1),
        '7' to listOf(1 to 0, 0 to -1),
        'F' to listOf(1 to 0, 0 to 1),
    )

    fun part1(input: List<String>): Int {
        val (n, m) = input.size to input[0].length
        val start = input.indexOfFirst { 'S' in it }.let { row -> row to input[row].indexOf('S') }
        val stepsArray = Array(n) { IntArray(m) { Int.MAX_VALUE } }.also { it[start.first][start.second] = 0 }

        val queue = ArrayDeque(listOf(start))
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            val (pipe, steps) = input[row][col] to stepsArray[row][col]
            val connection = connectionsByPipes[pipe] ?: continue
            connection.map { (dr, dc) -> row + dr to col + dc }
                .filter { (r, c) -> stepsArray[r][c] > steps + 1 }
                .filter { (r, c) ->
                    input.getOrNull(r, c)?.let {
                        connectionsByPipes[it]?.any { (dr, dc) -> row to col == r + dr to c + dc }
                    } ?: false
                }.forEach { (r, c) ->
                    stepsArray[r][c] = steps + 1
                    queue.add(r to c)
                }
        }

        return stepsArray.maxOf { it.filterNot { steps -> steps == Int.MAX_VALUE }.maxOrNull() ?: 0 }
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    solve(23, 10, ::part1, ::part2, 4, 2)
}