package app.jonas.adventofcode2018.d08

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class MemoryManeuverTest {
    @Test
    fun testPart1Example() = assertEquals(138, part1(load("example.txt")))

    @Test
    fun testPart1Input() = assertEquals(47464, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(66, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(23054, part2(load("input.txt")))
}
