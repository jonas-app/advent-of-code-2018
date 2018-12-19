package app.jonas.adventofcode2018.d09

import java.io.File

val pattern = Regex("(\\d+) players; last marble is worth (\\d+) points")

// misunderstood the instructions and reused the removed marble in the next round
class MarbleIterator(private val lastMarbleValue: Int) : Iterator<Int> {
    var additionalMarble: Int? = null
    private var nextMarble = 1
    override fun hasNext() = additionalMarble != null || nextMarble <= lastMarbleValue
    override fun next() = additionalMarble?.also { additionalMarble = null } ?: nextMarble++
}

fun isWinningRound(marble: Int) = marble % 23 == 0

fun part1(file: File): Int {
    val inputLine = file.readText().dropLast(1)
    val (players, lastMarbleValue) = pattern.matchEntire(inputLine)?.groupValues?.drop(1)?.map(String::toInt)
        ?: throw Error("Input not parsable!")
    val circle = ArrayList<Int>(lastMarbleValue - (lastMarbleValue / 23))
    circle.add(0)
    var currentMarble = 0
    val marbleIterator = MarbleIterator(lastMarbleValue)
    val scores = Array(players) {0} // index moved by 1
    var round = 1
    while (marbleIterator.hasNext()) {
        val marble = marbleIterator.next()
        if (isWinningRound(marble)) {
            val currentPlayer = (round + players - 1) % players
            // the index to remove gets the new current index after removing
            currentMarble = (currentMarble - 7 + circle.size) % circle.size
            val removedValue = circle.removeAt(currentMarble)
            // marbleIterator.additionalMarble = removedValue
            scores[currentPlayer] += marble + removedValue
        } else {
            currentMarble = (currentMarble + 1 + circle.size) % circle.size + 1
            circle.add(currentMarble, marble)
        }
        // printRound(round, circle, currentMarble)
        round++
    }
    return scores.max() ?: throw Error("No highscore!")
}

// [1]    0 (  1)
private fun printRound(round: Int, circle: ArrayList<Int>, currentMarble: Int) {
    print("[${round.toString().padStart(2, ' ')}] ")
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
