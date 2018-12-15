package app.jonas.adventofcode2018.d06

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty

val pattern = Regex("(\\d+), (\\d+)")

class MinMaxDel(private val rangeProperty: KMutableProperty0<IntRange?>) {
    operator fun getValue(thisRef: Plane, property: KProperty<*>): Nothing = throw Error("access the ranges")
    operator fun setValue(thisRef: Plane, property: KProperty<*>, value: Int) =
        rangeProperty.set(rangeProperty.get()?.let { min(value, it.first)..max(value, it.last) } ?: value..value)
}

class Plane {
    var xRange: IntRange? = null
        private set
    var yRange: IntRange? = null
        private set
    var x: Int by MinMaxDel(::xRange)
    var y: Int by MinMaxDel(::yRange)
    fun addPoint(pair: Pair<Int, Int>) {
        x = pair.first
        y = pair.second
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
    val xMinMax = MinMax()
    val yMinMax = MinMax()
    val points = file.readLines().mapNotNull {
        pattern.matchEntire(it)?.groupValues?.drop(1)?.map(String::toInt)?.let { (x, y) ->
            xMinMax(x)
            yMinMax(y)
            x to y
        }
    }
    val infinite = mutableSetOf<Int>()
    val counts = mutableMapOf<Int, Int>()
    for (y in yMinMax.range) for (x in xMinMax.range) {
        val (n1, n2) = points.mapIndexed { i, (px, py) -> i to abs(x - px) + abs(y - py) }.sortedBy { it.second }
        val (i, d) = n1
        if (d != n2.second) {
            if (xMinMax.onBoundary(x) || yMinMax.onBoundary(y)) {
                infinite.add(i)
            }
            counts[i] = (counts[i] ?: 0) + 1
        }
    }
    infinite.forEach { counts.remove(it) }
    return counts.values.max() ?: throw IllegalArgumentException("Max not found")
}

fun part1o(file: File): Int {
    var minX = Int.MAX_VALUE
    var maxX = Int.MIN_VALUE
    var minY = Int.MAX_VALUE
    var maxY = Int.MIN_VALUE
    val points =
        file.readLines().mapNotNull {
            pattern.matchEntire(it)?.groupValues?.drop(1)?.map(String::toInt)?.let { (x, y) ->
                minX = min(x, minX)
                maxX = max(x, maxX)
                minY = min(y, minY)
                maxY = max(y, maxY)
                x to y
            }
        }
    //val inside = points.filter { (x, y) -> x != minX && x != maxX && y != minY && y != maxY }
    val infinite = mutableSetOf<Int>()
    val counts = mutableMapOf<Int, Int>()
    for (y in minY..maxY) {
        for (x in minX..maxX) {
            val (n1, n2) = points.mapIndexed { i, (px, py) -> i to abs(x - px) + abs(y - py) }.sortedBy { it.second }
            val (i, d) = n1
            if (d != n2.second) {
                if (x == minX || x == maxX || y == minY || y == maxY) {
                    infinite.add(i)
                }
                counts[i] = (counts[i] ?: 0) + 1
                toLetter(i)
            }
        }
    }
    infinite.forEach { counts.remove(it) }
    return counts.values.max() ?: throw IllegalArgumentException("Max not found")
}

fun toLetter(i: Int) = (i % 26 + 97).toChar()

fun part2(file: File): Int {
    println(file.readText())
    return 2
}
