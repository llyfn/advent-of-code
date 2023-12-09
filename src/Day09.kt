fun main() {
    fun List<Long>.getDiff() = zipWithNext().map { (a, b) -> b - a }

    fun part1(input: List<String>): Long = input.map { it.getLongs() }.sumOf { sequence ->
        var (curr, diff) = sequence.last() to sequence.getDiff()
        while (!diff.all { it == 0L }) {
            curr += diff.last()
            diff = diff.getDiff()
        }
        curr
    }

    fun part2(input: List<String>): Long = input.map { it.getLongs() }.sumOf { sequence ->
        var (curr, diff) = sequence.first() to sequence.getDiff()
        var multiplier = -1
        while (!diff.all { it == 0L }) {
            curr += multiplier * diff.first()
            diff = diff.getDiff()
            multiplier = -multiplier
        }
        curr
    }

    check(part1(readInput("Day09_test")) == 114L)
    check(part2(readInput("Day09_test")) == 2L)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
