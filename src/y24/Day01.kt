package y24

import getInts
import solve
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val s1 = mutableListOf<Int>()
        val s2 = mutableListOf<Int>()
        input.forEach {
            val (a,b) = it.getInts()
            s1.add(a)
            s2.add(b)
        }
        return s1.sorted().zip(s2.sorted()).sumOf { (a,b) -> abs(a - b) }
    }

    fun part2(input: List<String>): Int {
        val s1 = mutableListOf<Int>()
        val s2 = mutableListOf<Int>()
        input.forEach {
            val (a,b) = it.getInts()
            s1.add(a)
            s2.add(b)
        }
        return s1.sumOf { i -> i * s2.count { it == i } }
    }

    solve(24, 1, ::part1, ::part2, 11, 31)
}
