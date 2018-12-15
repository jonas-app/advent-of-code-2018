package app.jonas.adventofcode2018.d05

import java.io.File

// 24s 612ms
fun reactOld(input: String): String {
    var skip = false
    var found = false
    val output = input.windowed(2).fold(input) { acc, it ->
        if (skip) {
            skip = false
            return@fold acc
        }
        if (it[0].equals(it[1], true) && it[0].isLowerCase() != it[1].isLowerCase()) {
            found = true
            skip = true
            return@fold acc.replaceFirst(it, "")
        }
        acc
    }
    return if (found) reactOld(output) else output
}

// 8s 700ms
tailrec fun react(input: String): String {
    var skip = false
    val length = input.length
    val output = input.zipWithNext().fold(StringBuilder(length)) { acc, (a, b) ->
        if (!skip && (!a.equals(b, true) || a.isLowerCase() == b.isLowerCase())) {
            acc.append(a)
        } else {
            skip = !skip
        }
        acc
    }.apply { if (!skip) append(input.last()) }.toString()
    return if (output.length < length) react(output) else output
}

fun part1(file: File) = react(file.readLines().single()).length

fun part2(file: File): Int? {
    val input = file.readLines().single()
    return input.toList().distinctBy { it.toLowerCase() }
        .map { react(input.replace(it.toString(), "", true)).length }
        .min()
}
