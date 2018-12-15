package app.jonas.adventofcode2018.d02

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class InventoryManagementSystemTest {
    @Test
    fun testPart1Example() = assertEquals(12, part1(load("example1.txt")))

    @Test
    fun testPart1Input() = assertEquals(8820, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals("fgij", part2(load("example2.txt")))

    @Test
    fun testPart2Example1() = assertEquals("abcde", part2(load("example1.txt")))

    @Test
    fun testPart2Input() = assertEquals("bpacnmglhizqygfsjixtkwudr", part2(load("input.txt")))
}
