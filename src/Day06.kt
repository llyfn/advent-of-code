import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    fun solveQuadraticEquation(a: Double, b: Double, c: Double): Pair<Double, Double> =
        (-b - sqrt(b.pow(2) - 4 * a * c)) / (2 * a) to (-b + sqrt(b.pow(2) - 4 * a * c)) / (2 * a)

    fun part1(input: List<String>): Int {
        val times = input[0].getInts()
        val distances = input[1].getInts()

        return times.zip(distances).productOf { (time, distance) ->
            val (min, max) = solveQuadraticEquation(1.0, -time.toDouble(), distance.toDouble())
            ceil(max - 1).toInt() - floor(min + 1).toInt() + 1
        }
    }

    fun part2(input: List<String>): Long {
        val time = input[0].getInts().joinToString("").toLong()
        val distance = input[1].getInts().joinToString("").toLong()

        val (min, max) = solveQuadraticEquation(1.0, -time.toDouble(), distance.toDouble())
        return ceil(max - 1).toLong() - floor(min + 1).toLong() + 1
    }

    solve(6, ::part1, ::part2, 288, 71503L)
}
