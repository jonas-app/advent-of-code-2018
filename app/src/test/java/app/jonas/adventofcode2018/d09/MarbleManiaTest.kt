package app.jonas.adventofcode2018.d09

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class MarbleManiaTest {
    @Test
    fun testPart1Example() = assertEquals(32, part1(load("example.txt")))

    @Test
    fun testPart1Example1() = assertEquals(8317, part1(load("example1.txt")))

    @Test
    fun testPart1Example2() = assertEquals(146373, part1(load("example2.txt")))

    @Test
    fun testPart1Example3() = assertEquals(2764, part1(load("example3.txt")))

    @Test
    fun testPart1Example4() = assertEquals(54718, part1(load("example4.txt")))

    @Test
    fun testPart1Example5() = assertEquals(37305, part1(load("example5.txt")))

    @Test
    fun testPart1Input() = assertEquals(400493, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(22563, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(3338341690, part2(load("input.txt")))
}
