package app.jonas.adventofcode2018.d04

import java.io.File

// [1518-11-01 23:58] Guard #99 begins shift
val pattern = Regex("\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)] (.*)")
val beginsShiftPattern = Regex("Guard #(\\d+) begins shift")

data class Entry(val time: List<Int>, val command: String) : Comparable<Entry> {
    override fun compareTo(other: Entry): Int =
        compareBy(*(0..time.size).map { { entry: Entry -> entry.time[it] } }.toTypedArray()).compare(this, other)
    //compareBy({ it.time[0] }, { it.time[1] }, { it.time[2] }, { it.time[3] }, { it.time[4] })
}

fun parseInput(file: File): List<Entry> {
    return file.readLines().mapNotNull {
        pattern.matchEntire(it)?.groupValues?.drop(1)?.withIndex()?.partition { (index) -> index < 5 }
            ?.let { (time, command) ->
                val timeParsed = time.map { indexed -> indexed.value.toInt() }
                Entry(timeParsed, command.single().value)
            }
    }
}

fun extractSleepRanges(entries: List<Entry>): MutableMap<Int, MutableList<IntRange>> {
    val initial = -1 to mutableMapOf<Int, MutableList<IntRange>>()
    val (_, sleepRanges) = entries.fold(initial) { (lastId, map), (time, command) ->
        val list = map[lastId];
        when (command) {
            "falls asleep" -> {
                val range = time.last() until -1
                if (list == null) map[lastId] = mutableListOf(range) else list.add(range)
            }
            "wakes up" -> list?.set(list.size - 1, list.last().first until time.last())
            else -> {
                val id = beginsShiftPattern.matchEntire(command)?.groupValues?.get(1)?.toInt()
                    ?: throw IllegalArgumentException("Unknown command: $command")
                return@fold id to map
            }
        }
        lastId to map
    }
    return sleepRanges
}

fun sleepiestMinute(ranges: List<IntRange>) =
    (0..59).map { it to ranges.fold(0) { acc, range -> if (it in range) acc + 1 else acc } }
        .maxBy { (_, count) -> count }
        ?: throw IllegalArgumentException("Sleepiest min not parsable")

fun part1(file: File): Int {
    val sortedInput = parseInput(file).sorted()
    val sleepRanges = extractSleepRanges(sortedInput)
    val (id, ranges) = sleepRanges.maxBy { (_, ranges) -> ranges.fold(0) { acc, r -> acc + r.count() } }
        ?: throw IllegalArgumentException("Sleep ranges not parsable")
    return id * sleepiestMinute(ranges).first
}

fun part2(file: File): Int {
    val sortedInput = parseInput(file).sorted()
    val sleepRanges = extractSleepRanges(sortedInput)
    val (id, min) = sleepRanges.map { (id, ranges) ->
        val (minute, count) = sleepiestMinute(ranges)
        Triple(id, minute, count)
    }.maxBy { (_, _, count) -> count } ?: throw IllegalArgumentException("Sleep ranges not parsable")
    return id * min
}