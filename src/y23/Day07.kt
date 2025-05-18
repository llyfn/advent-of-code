package y23

import solve

fun main() {
    fun List<String>.getWinnings(withJoker: Boolean) =
        map { line -> line.split(" ").let { (cards, bid) -> Hand(cards, withJoker) to bid.toInt() } }
            .sortedBy { (hand, _) -> hand }
            .mapIndexed { idx, (_, bid) -> bid * (idx + 1) }
            .sum()

    fun part1(input: List<String>): Int = input.getWinnings(withJoker = false)
    fun part2(input: List<String>): Int = input.getWinnings(withJoker = true)

    solve(23, 7, ::part1, ::part2, 6440, 5905)
}

data class Hand(val cards: String, val withJoker: Boolean) : Comparable<Hand> {
    enum class HandType { HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND }

    fun type(): HandType {
        val groupedCards = cards.groupingBy { it }.eachCount()
        if (withJoker && groupedCards['J'] != null) {
            return when (groupedCards.size) {
                1, 2 -> HandType.FIVE_OF_A_KIND
                3 -> {
                    if (3 in groupedCards.values || groupedCards['J'] != 1) HandType.FOUR_OF_A_KIND
                    else HandType.FULL_HOUSE
                }
                4 -> HandType.THREE_OF_A_KIND
                else -> HandType.ONE_PAIR
            }
        }
        return when (groupedCards.size) {
            1 -> HandType.FIVE_OF_A_KIND
            2 -> if (4 in groupedCards.values) HandType.FOUR_OF_A_KIND else HandType.FULL_HOUSE
            3 -> if (3 in groupedCards.values) HandType.THREE_OF_A_KIND else HandType.TWO_PAIR
            4 -> HandType.ONE_PAIR
            else -> HandType.HIGH_CARD
        }
    }

    override fun compareTo(other: Hand): Int {
        if (type() != other.type()) return type().compareTo(other.type())
        cards.zip(other.cards).forEach { (c1, c2) -> if (c1.str() != c2.str()) return c1.str().compareTo(c2.str()) }
        return 0
    }

    private fun Char.str() = strengthOrder.indexOf(this)
    private val strengthOrder =
        if (withJoker) listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')
        else listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
}