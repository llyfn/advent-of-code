package y24

import getInts
import solve

fun main() {
    fun part1(input: List<String>): Int =
        input.map { it.getInts() }.sumOf {
            val w = it.windowed(2)
            if (w.all {(a,b) -> a - b in -3..-1} || w.all {(a,b) -> a - b in 1..3 }) 1 as Int else 0
    }

    fun part2(input: List<String>): Int {
        return input.map { it.getInts() }.sumOf {
            val w = List(it.size) { i -> it.filterIndexed { j, _ -> i != j }.windowed(2) }
            if (w.any { l -> l.all {(a,b) -> a - b in -3..-1} || l.all {(a,b) -> a - b in 1..3 }}) 1 as Int else 0
        }
    }
    solve(24, 2, ::part1, ::part2, 2, 4)
}