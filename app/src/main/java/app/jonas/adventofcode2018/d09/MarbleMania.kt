package app.jonas.adventofcode2018.d09

import java.io.File

val pattern = Regex("(\\d+) players; last marble is worth (\\d+) points")

fun isWinningRound(marble: Int) = marble % 23 == 0

fun part1(file: File): Int {
    val inputLine = file.readText().dropLast(1)
    val (players, lastMarbleValue) = pattern.matchEntire(inputLine)?.groupValues?.drop(1)?.map(String::toInt)
        ?: throw Error("Input not parsable!")
    val circle = ArrayList<Int>(lastMarbleValue - (lastMarbleValue / 23))
    circle.add(0)
    var currentMarble = 0
    val scores = Array(players) {0} // index moved by 1
    var marble = 1
    while (marble <= lastMarbleValue) {
        if (isWinningRound(marble)) {
            val currentPlayer = (marble + players - 1) % players
            // the index to remove gets the new current index after removing
            currentMarble = (currentMarble - 7 + circle.size) % circle.size
            val removedValue = circle.removeAt(currentMarble)
            scores[currentPlayer] += marble + removedValue
        } else {
            currentMarble = (currentMarble + 1 + circle.size) % circle.size + 1
            circle.add(currentMarble, marble)
        }
        // printRound(marble, circle, currentMarble)
        marble++
    }
    return scores.max() ?: throw Error("No highscore!")
}

// [1]    0 (  1)
private fun printRound(marble: Int, circle: ArrayList<Int>, currentMarble: Int) {
    print("[${marble.toString().padStart(2, ' ')}] ")
    circle.forEachIndexed { i, m ->
        print(if (i == currentMarble) "(" else " ")
        print(m.toString().padStart(3, ' '))
        print(if (i == currentMarble) ")" else " ")
    }
    println()
}

fun part2(file: File): Int {
    return 1
}
