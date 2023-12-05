import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

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
fun String.findNumbers() = Regex("\\d+").findAll(this)

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