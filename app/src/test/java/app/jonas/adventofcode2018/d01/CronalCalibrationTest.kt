package app.jonas.adventofcode2018.d01

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class CronalCalibrationTest {
    @Test
    fun testPart1Example() = assertEquals(3, part1(load("example.txt")))

    @Test
    fun testPart1Input() = assertEquals(454, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(2, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(566, part2(load("input.txt")))
}
