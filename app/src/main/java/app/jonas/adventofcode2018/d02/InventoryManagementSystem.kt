package app.jonas.adventofcode2018.d02

import java.io.File

fun part1(file: File): Int {
    var two = 0
    var three = 0
    file.readLines().forEach {
        val counts = it.groupingBy { c -> c }.eachCount()
        if (counts.containsValue(2)) two++
        if (counts.containsValue(3)) three++

    }
    return two * three
}

fun part2(file: File): String {
    val mutableBoxIds = file.readLines().toMutableList()
    while (true) {
        val boxId = mutableBoxIds.removeAt(0)
        mutableBoxIds.forEach { otherBoxId ->
            return boxId.foldIndexed("") { i, equalPart, char ->
                when {
                    char == otherBoxId[i] -> equalPart + char
                    equalPart.length < i -> return@forEach // differs by more than one char
                    else -> equalPart
                }
            }
        }
    }
}