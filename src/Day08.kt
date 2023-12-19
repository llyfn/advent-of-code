fun main() {
    fun List<String>.parseMaps() = associate { line ->
        Regex("[A-Z0-9]+").findAll(line).map { it.value }.toList().let { (src, l, r) -> src to (l to r) }
    }

    fun List<String>.traverse(
        start: String,
        idx: Long = 0,
        arrived: (String) -> Boolean
    ): Pair<String, Long> {
        val (inst, maps) = first() to drop(2).parseMaps()
        var (curr, cnt) = start to idx
        do {
            curr = maps.getValue(curr).let { (l, r) -> if (inst[(cnt % inst.length).toInt()] == 'L') l else r }
            cnt++
        } while (!arrived(curr))
        return curr to cnt
    }

    fun part1(input: List<String>): Long = input.traverse("AAA") { it == "ZZZ" }.second

    fun part2(input: List<String>): Long {
        val sources = input.drop(2).parseMaps().keys.filter { it.endsWith('A') }
        val firstArrivalAndPeriods = sources.map { src -> input.traverse(src) { it.endsWith('Z') } }
            .map { (node, steps) -> steps to input.traverse(node, steps) { it == node }.second - steps }
            .sortedBy { it.second }

        var multiplier = 0L
        while (true) {
            val steps = firstArrivalAndPeriods[0].let { (fa, p) -> fa + p * multiplier }
            if (firstArrivalAndPeriods.all { (fa, p) -> (steps - fa) % p == 0L }) return steps
            multiplier++
        }
    }

    solve(8, ::part1, ::part2, 2, 6)
}