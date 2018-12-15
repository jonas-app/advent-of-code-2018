package app.jonas.adventofcode2018.d01

import java.io.File

fun part1(file: File) = file.readLines().fold(0) { acc, s -> acc + s.toInt() }

fun part2(file: File): Int {
    val prev = mutableListOf<Int>()
    var res = 0
    var i = 0
    val input = file.readLines()
    while (!prev.contains(res)) {
        prev.add(res)
        res += input[i % input.size].toInt()
        i++
    }
    return res
}