package app.jonas.adventofcode2018.d08

import java.io.File

class Node(iterator: Iterator<String>) {
    private val childCount = iterator.next().toInt()
    private val metadataCount = iterator.next().toInt()
    private val childs = Array(childCount) { Node(iterator) }
    private val metadata = Array(metadataCount) { iterator.next().toInt() }
    val overallMetaDataSum: Int
        get() = metadata.sum() + childs.sumBy { it.overallMetaDataSum }
    val value: Int
        get() = if (childCount == 0) {
            metadata.sum()
        } else {
            metadata.fold(0) { sum, i -> sum + (childs.getOrNull(i - 1)?.value ?: 0) }
        }
}

fun part1(file: File): Int {
    val iterator = file.readText().dropLast(1).split(' ').iterator()
    return Node(iterator).overallMetaDataSum
}

fun part2(file: File): Int {
    val iterator = file.readText().dropLast(1).split(' ').iterator()
    return Node(iterator).value
}
