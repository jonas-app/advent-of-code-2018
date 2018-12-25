package app.jonas.adventofcode2018.d11

import org.junit.Assert.assertEquals
import org.junit.Test

class TheStarsAlignTest {
    @Test
    fun testCalculatePowerLevel1() = assertEquals(4, calculatePowerLevel(x = 3, y = 5, serialNumber = 8))

    @Test
    fun testCalculatePowerLevel2() = assertEquals(-5, calculatePowerLevel(x = 122, y = 79, serialNumber = 57))

    @Test
    fun testCalculatePowerLevel3() = assertEquals(0, calculatePowerLevel(x = 217, y = 196, serialNumber = 39))

    @Test
    fun testCalculatePowerLevel4() = assertEquals(4, calculatePowerLevel(x = 101, y = 153, serialNumber = 71))

    @Test
    fun testPart1Example1() = assertEquals("33,45", part1(18))

    @Test
    fun testPart1Example2() = assertEquals("21,61", part1(42))

    @Test
    fun testPart1Input() = assertEquals("243,64", part1(5468))

    @Test
    fun testPart2Example() = assertEquals("0,0", part2(8))

    @Test
    fun testPart2Input() = assertEquals("0,0", part2(5468))
}
