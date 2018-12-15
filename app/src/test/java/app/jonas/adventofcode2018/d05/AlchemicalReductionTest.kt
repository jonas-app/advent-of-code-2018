package app.jonas.adventofcode2018.d05

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class AlchemicalReductionTest {
    @Test
    fun testPart1Example() = assertEquals(10, part1(load("example.txt")))

    @Test
    fun testPart1Input() = assertEquals(9526, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(4, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(6694, part2(load("input.txt")))
}
