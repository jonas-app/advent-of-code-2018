package app.jonas.adventofcode2018.d06

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class ChronalCoordinatesTest {
    @Test
    fun testPart1Example() = assertEquals(17, part1(load("example.txt")))

    @Test
    fun testPart1Testing() = assertEquals(31, part1(load("testing.txt")))

    @Test
    fun testPart1Input() = assertEquals(5975, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(16, part2(load("example.txt")))

    @Test
    fun testPart2Testing() = assertEquals(0, part2(load("testing.txt")))

    @Test
    fun testPart2Input() = assertEquals(0, part2(load("input.txt")))
}
