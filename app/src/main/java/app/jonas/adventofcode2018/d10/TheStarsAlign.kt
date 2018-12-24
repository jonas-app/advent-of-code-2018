package app.jonas.adventofcode2018.d10

import app.jonas.adventofcode2018.d06.MinMax
import java.io.File

//position=< 3, -2> velocity=<-1,  1>
val pattern = Regex("position=< *([-]?)(\\d+), *([-]?)(\\d+)> velocity=< *([-]?)(\\d+), *([-]?)(\\d+)>")

// [" ", "3"] -> 3; ["-", "2"] -> -2
fun extractSignedValue(input: List<String>): Int {
    val (sign, value) = input
    return value.toInt() * if (sign == "-") -1 else 1
}

typealias Position = Pair<Int, Int>
typealias Velocity = Pair<Int, Int>
typealias Point = Pair<Position, Velocity>

operator fun Position.plus(velocity: Velocity): Position {
    val (x, y) = this
    val (xV, yV) = velocity
    return (x + xV) to (y + yV)
}

class Plane() {
    private val points: MutableList<Point> = mutableListOf()
    private val positions: MutableSet<Position> = hashSetOf()
    private val xMinMax = MinMax()
    private val yMinMax = MinMax()

    constructor(file: File) : this() {
        file.readLines().forEach { line ->
            val parsedLine = pattern.matchEntire(line)?.groupValues?.drop(1) ?: throw Error("Line not parsable!")
            val (x, y, xV, yV) = parsedLine.windowed(2, 2).map(::extractSignedValue)
            addPoint(x to y, xV to yV)
        }
    }

    private fun addPoint(position: Position, velocity: Velocity) {
        points += position to velocity
        positions.add(position)
        val (x, y) = position
        xMinMax(x)
        yMinMax(y)
    }

    fun update(): Plane {
        return points.fold(Plane()) { plane, (position, velocity) ->
            val newPosition = position + velocity
            plane.addPoint(newPosition, velocity)
            plane
        }
    }

    fun size() = xMinMax.range.count().toLong() * yMinMax.range.count()

    override fun toString(): String {
        val builder = StringBuilder()
        // builder.appendln("=".repeat(xMinMax.range.count()))
        for (y in yMinMax.range) {
            for (x in xMinMax.range) {
                val isPoint = positions.contains(x to y)
                builder.append(if (isPoint) '#' else '.')
            }
            builder.appendln()
        }
        return builder.toString()
    }
}

fun part1(file: File): String {
    val (_, message) = fastForward(file)
    return message
}

private fun fastForward(file: File): Pair<Int, String> {
    var plane = Plane(file)
    var size = plane.size()
    var i = 0
    while (true) {
        val newPlane = plane.update()
        val newSize = newPlane.size()
        if (newSize > size) return i to plane.toString()
        plane = newPlane
        size = newSize
        i++
    }
}

fun part2(file: File): Int {
    val (seconds) = fastForward(file)
    return seconds
}
