package y24

import solve

fun main() {
    fun part1(input: List<String>): Int = input.indices.sumOf { i ->
        input.first().indices.sumOf { j ->
            val s = buildList {
                if (i > 2) add(String(charArrayOf(input[i - 3][j], input[i - 2][j], input[i - 1][j], input[i][j])))
                if (i < input.size - 3) add(
                    String(charArrayOf(input[i][j], input[i + 1][j], input[i + 2][j], input[i + 3][j]))
                )
                if (j > 2) add(input[i].substring(j - 3, j + 1))
                if (j < input.first().length - 3) add(input[i].substring(j, j + 4))
                if (i > 2 && j > 2) add(
                    String(charArrayOf(input[i - 3][j - 3], input[i - 2][j - 2], input[i - 1][j - 1], input[i][j]))
                )
                if (i < input.size - 3 && j < input.first().length - 3) add(
                    String(charArrayOf(input[i][j], input[i + 1][j + 1], input[i + 2][j + 2], input[i + 3][j + 3]))
                )
                if (i > 2 && j < input.first().length - 3) add(
                    String(charArrayOf(input[i - 3][j + 3], input[i - 2][j + 2], input[i - 1][j + 1], input[i][j]))
                )
                if (i < input.size - 3 && j > 2) add(
                    String(charArrayOf(input[i][j], input[i + 1][j - 1], input[i + 2][j - 2], input[i + 3][j - 3]))
                )
            }
            s.count { it == "XMAS" || it == "SAMX" }
        }
    } / 2

    fun part2(input: List<String>): Int {
        var count = 0
        for (i in 0..input.size - 3) {
            for (j in 0..input.first().length - 3) {
                val s1 = String(charArrayOf(input[i][j], input[i + 1][j + 1], input[i + 2][j + 2]))
                val s2 = String(charArrayOf(input[i][j + 2], input[i + 1][j + 1], input[i + 2][j]))
                if ((s1 == "MAS" || s1 == "SAM") && (s2 == "MAS" || s2 == "SAM")) count++
            }
        }
        return count
    }

    solve(24, 4, ::part1, ::part2, 18, 9)
}