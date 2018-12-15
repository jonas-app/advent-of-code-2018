package app.jonas.adventofcode2018.d06

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

val pattern = Regex("(\\d+), (\\d+)")

class Plane(file: File) {
    val xMinMax = MinMax()
    val yMinMax = MinMax()
    val points = file.readLines().mapNotNull {
        pattern.matchEntire(it)?.groupValues?.drop(1)?.map(String::toInt)?.let { (x, y) ->
            xMinMax(x)
            yMinMax(y)
            x to y
        }
    }
}

class MinMax {
    @Suppress("EmptyRange")
    var range: IntRange = Int.MAX_VALUE..Int.MIN_VALUE
        private set

    operator fun invoke(value: Int) {
        range = min(value, range.first)..max(value, range.last)
    }
}

fun MinMax.onBoundary(value: Int) = value == this.range.first || value == this.range.last

fun part1(file: File): Int {
    val plane = Plane(file)
    val infinite = mutableSetOf<Int>()
    val counts = mutableMapOf<Int, Int>()
    for (y in plane.yMinMax.range) for (x in plane.xMinMax.range) {
        val (n1, n2) = plane.points.mapIndexed { i, (px, py) -> i to abs(x - px) + abs(y - py) }.sortedBy { it.second }
        val (i, d) = n1
        if (d != n2.second) {
            if (plane.xMinMax.onBoundary(x) || plane.yMinMax.onBoundary(y)) {
                infinite.add(i)
            }
            counts[i] = (counts[i] ?: 0) + 1
        }
    }
    infinite.forEach { counts.remove(it) }
    return counts.values.max() ?: throw IllegalArgumentException("Max not found")
}

fun part2(file: File, maxDistance: Int): Int {
    val plane = Plane(file)
    var size = 0
    for (y in plane.yMinMax.range) for (x in plane.xMinMax.range) {
        val distance = plane.points.fold(0) { acc, (px, py) -> acc + abs(x - px) + abs(y - py) }
        if (distance < maxDistance) size++
    }
    return size
}
