import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Finds all numbers in the string.
 */
fun String.findNumbers() = Regex("-?\\d+").findAll(this)

/**
 * Converts string to integer list.
 */
fun String.getInts() = findNumbers().map { it.value.toInt() }.toList()

/**
 * Converts string to long list.
 */
fun String.getLongs() = findNumbers().map { it.value.toLong() }.toList()

/**
 * The shorthand for getting a character at the given position in the list of strings.
 */
fun List<CharSequence>.getOrNull(row: Int, col: Int): Char? = getOrNull(row)?.getOrNull(col)

/**
 * Returns the product of all elements in the collection.
 */
fun Iterable<Int>.product(): Int = fold(1) { acc, it -> acc * it }

/**
 * Returns the product of all values produced by selector function applied to each element in the collection.
 */
inline fun <T> Iterable<T>.productOf(selector: (T) -> Int): Int = fold(1) { acc, it -> acc * selector(it) }

/**
 * Runs solution on the input for the given day.
 */
inline fun <T, S> solve(year: Int, day: Int, pt1: (List<String>) -> T, pt2: (List<String>) -> S, t1Ans: T, t2Ans: S? = null) {
    val paddedDay = day.toString().padStart(2, '0')
    val input = readInput("y$year/Day$paddedDay")
    val testInput = runCatching { readInput("y$year/Day${paddedDay}_test") }.getOrNull()

    check(pt1(testInput ?: readInput("y$year/Day${paddedDay}_1_test")) == t1Ans)
    t2Ans?.let { check(pt2(testInput ?: readInput("y$year/Day${paddedDay}_2_test")) == it) }

    pt1(input).println()
    pt2(input).println()
}
