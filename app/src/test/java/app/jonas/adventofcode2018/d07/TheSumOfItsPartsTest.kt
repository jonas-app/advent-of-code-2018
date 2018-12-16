package app.jonas.adventofcode2018.d07

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

class TheSumOfItsPartsTest {
    @Test
    fun testPart1Example() = assertEquals("CABDFE", part1(load("example.txt")))

    @Test
    fun testPart1Input() = assertEquals("EUGJKYFQSCLTWXNIZMAPVORDBH", part1(load("input.txt")))

    @Test
    fun testPart2Testing() = assertEquals(0, part2(load("testing.txt")))

    @Test
    fun testPart2Input() = assertEquals(0, part2(load("input.txt")))
}
