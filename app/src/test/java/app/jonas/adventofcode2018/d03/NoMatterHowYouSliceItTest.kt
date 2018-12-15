package app.jonas.adventofcode2018.d03

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class NoMatterHowYouSliceItTest {
    @Test
    fun testPart1Example() = assertEquals(4, part1(load("example.txt")))

    @Test
    fun testPart1Testing() = assertEquals(4, part1(load("testing.txt")))

    @Test
    fun testPart1Input() = assertEquals(116491, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(3, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(707, part2(load("input.txt")))
}
