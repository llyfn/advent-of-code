fun main() {
    fun parseMaps(input: List<String>) = buildList {
        var maps = input
        while (maps.isNotEmpty()) {
            val map = maps.takeWhile { it.isNotBlank() }
            maps = maps.drop(map.size + 1)
            add(map.drop(1).map { it.getLongs().let { (dst, src, len) -> src..<src + len to dst } })
        }
    }

    fun LongRange.complement(ranges: List<LongRange>): List<LongRange> = buildList {
        var currStart = first
        ranges.sortedBy { it.first }.forEach {
            if (it.first > currStart) add(currStart..<it.first)
            currStart = it.last + 1
        }
        if (currStart < last) add(currStart..last)
    }

    fun part1(input: List<String>): Long {
        val seeds = input[0].substringAfter(": ").getLongs()
        val maps = parseMaps(input.drop(2))
        return maps.fold(seeds) { acc, map ->
            acc.map { src -> map.find { src in it.first }?.let { it.second + src - it.first.first } ?: src }
        }.min()
    }

    fun part2(input: List<String>): Long {
        val seedRanges = input[0].substringAfter(": ").getLongs().chunked(2).map { (src, len) -> src..<src + len }
        val maps = parseMaps(input.drop(2))
        return maps.fold(seedRanges) { acc, map ->
            acc.flatMap { srcRange ->
                val (dstRanges, mapped) = mutableListOf<LongRange>() to mutableListOf<LongRange>()
                map.forEach { (range, dst) ->
                    val min = srcRange.first.coerceAtLeast(range.first)
                    val max = srcRange.last.coerceAtMost(range.last)
                    if (min <= max) {
                        mapped += min..max
                        dstRanges += (min - range.first + dst)..(max - range.first + dst)
                    }
                }
                dstRanges.also { it += srcRange.complement(mapped) }
            }
        }.minOf { it.min() }
    }

    solve(5, ::part1, ::part2, 35L, 46L)
}

