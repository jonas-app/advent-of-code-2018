package app.jonas.adventofcode2018.d04

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class ReposeRecordTest {
    @Test
    fun testPart1Example() = assertEquals(240, part1(load("example.txt")))

    @Test
    fun testPart1Input() = assertEquals(143415, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(4455, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(49944, part2(load("input.txt")))
}
