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
    fun testPart2Example() = assertEquals(15, part2(load("example.txt"), 2, 0))

    @Test
    fun testPart2Input() = assertEquals(1014, part2(load("input.txt"), 5, 60))
}
