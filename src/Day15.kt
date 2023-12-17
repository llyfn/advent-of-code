fun main() {
    fun hash(input: String): Int = input.fold(0) { acc, c -> (acc + c.code) * 17 and 255 }

    fun part1(input: List<String>): Int = input[0].split(",").sumOf(::hash)

    fun part2(input: List<String>): Int {
        val sequence = input[0].split(",")
        val slotsByHash = mutableMapOf<Int, MutableList<Pair<String, Int>>>()
        sequence.forEach { line ->
            if ('=' in line) {
                val (label, length) = line.split("=")
                val slots = slotsByHash.getOrPut(hash(label)) { mutableListOf() }
                slots.indexOfFirst { (l, _) -> l == label }.takeIf { it >= 0 }?.let { index ->
                    slots[index] = label to length.toInt()
                } ?: run {
                    slots.add(label to length.toInt())
                }
            } else {
                val label = line.dropLast(1)
                val slots = slotsByHash[hash(label)]
                slots?.indexOfFirst { (l, _) -> l == label }?.takeIf { it >= 0 }?.let { slots.removeAt(it) }
            }
        }

        return slotsByHash.map { (b, v) -> v.mapIndexed { idx, (_, len) -> (b + 1) * (idx + 1) * len }.sum() }.sum()
    }

    solve(15, ::part1, ::part2, 1320, 145)
}