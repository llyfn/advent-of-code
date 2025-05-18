package y23

import getInts
import solve

fun main() {
    fun getArrangements(springs: String, groups: List<Int>): Long {
        val mem = mutableMapOf<Triple<Int, Int, Int>, Long>()
        fun count(sCont: Int, sIdx: Int, gIdx: Int): Long = mem.getOrPut(Triple(sCont, sIdx, gIdx)) {
            if (sIdx >= springs.length) {
                return if (gIdx >= groups.size || gIdx == groups.size - 1 && sCont == groups[gIdx]) 1 else 0
            }
            var cnt = 0L
            if (springs[sIdx] in listOf('?', '.')) {
                if (gIdx < groups.size && groups[gIdx] == sCont) cnt += count(0, sIdx + 1, gIdx + 1)
                else if (sCont == 0) cnt += count(sCont, sIdx + 1, gIdx)
            }
            if (springs[sIdx] in listOf('?', '#') && gIdx < groups.size && sCont < groups[gIdx]) {
                cnt += count(sCont + 1, sIdx + 1, gIdx)
            }
            cnt
        }
        return count(0, 0, 0)
    }

    fun part1(input: List<String>): Long = input.sumOf {
        val (springs, groups) = it.split(" ")
        getArrangements(springs, groups.getInts())
    }

    fun part2(input: List<String>): Long = input.sumOf {
        val (springs, groups) = it.split(" ")
        getArrangements(List(5) { springs }.joinToString("?"), List(5) { groups.getInts() }.flatten())
    }

    solve(23, 12, ::part1, ::part2, 21L, 525152L)
}
