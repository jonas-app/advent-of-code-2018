package app.jonas.adventofcode2018.d09

import java.io.File

val pattern = Regex("(\\d+) players; last marble is worth (\\d+) points")

fun isWinningRound(marble: Int) = marble % 23 == 0

class Marble {
    val value: Int
    var previous: Marble
    var next: Marble
    constructor(value: Int) {
        this.value = value
        previous = this
        next = this
    }
    constructor(value: Int, previous: Marble, next: Marble) {
        this.value = value
        this.previous = previous
        this.next = next
    }
}

private fun getHighscore(file: File, lastMarbleValueMultiplier: Int = 1): Long {
    val inputLine = file.readText().dropLast(1)
    val (players, lastMarbleValue) = pattern.matchEntire(inputLine)?.groupValues?.drop(1)?.map(String::toInt)
        ?: throw Error("Input not parsable!")
    val lastMarbleValueMultiplied = lastMarbleValue * lastMarbleValueMultiplier
    val circle = ArrayList<Int>(lastMarbleValueMultiplied - (lastMarbleValueMultiplied / 23))
    circle.add(0)
    var currentMarble = Marble(0)
    val scores = LongArray(players) { 0 } // index moved by 1
    for (marble in 1..lastMarbleValueMultiplied) {
        if (isWinningRound(marble)) {
            val currentPlayer = (marble + players - 1) % players
            // val marbleToRemove = (1..6).fold(currentMarble) {acc, _ -> acc.previous }
            currentMarble = currentMarble.previous.previous.previous.previous.previous.previous
            val removedValue = currentMarble.previous.value
            currentMarble.previous = currentMarble.previous.previous
            currentMarble.previous.next = currentMarble
            scores[currentPlayer] += (marble + removedValue).toLong()
        } else {
            val previous = currentMarble.next
            val next = previous.next
            currentMarble = Marble(marble, previous, next)
            previous.next = currentMarble
            next.previous = currentMarble
        }
    }
    return scores.max() ?: throw Error("No highscore!")
}

fun part1(file: File)= getHighscore(file)

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

fun part2(file: File) = getHighscore(file, 100)
