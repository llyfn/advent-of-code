package y23

import solve

fun main() {
    fun getValue(line: String) = line.fold(0) { acc, char -> acc shl 1 or (if (char == '#') 1 else 0) }
    fun parse(input: List<String>) = buildList {
        var patterns = input
        while (patterns.isNotEmpty()) {
            val pattern = patterns.takeWhile { it.isNotBlank() }
            patterns = patterns.drop(pattern.size + 1)
            val rows = pattern.map(::getValue)
            val cols = pattern[0].indices.map { idx -> getValue(pattern.map { it[idx] }.joinToString("")) }
            add(rows to cols)
        }
    }

    fun List<Int>.getLineOfReflection(smudge: Boolean): Int =
        indices.find { idx ->
            if (idx == lastIndex) return@find false
            val range = 0..idx.coerceAtMost(lastIndex - idx - 1)
            if (smudge) range.sumOf { (get(idx - it) xor get(idx + 1 + it)).countOneBits() } == 1
            else range.all { get(idx - it) == get(idx + 1 + it) }
        }?.plus(1) ?: 0

    fun part1(input: List<String>): Int = parse(input).sumOf { (rows, cols) ->
        (rows.getLineOfReflection(false) * 100).takeIf { it > 0 } ?: cols.getLineOfReflection(false)
    }

    fun part2(input: List<String>): Int = parse(input).sumOf { (rows, cols) ->
        (rows.getLineOfReflection(true) * 100).takeIf { it > 0 } ?: cols.getLineOfReflection(true)
    }

    solve(23, 13, ::part1, ::part2, 405, 400)
}
