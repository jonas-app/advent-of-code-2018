package app.jonas.adventofcode2018.d10

import app.jonas.adventofcode2018.common.load
import org.junit.Assert.assertEquals
import org.junit.Test

val HI = """
#...#..###
#...#...#.
#...#...#.
#####...#.
#...#...#.
#...#...#.
#...#...#.
#...#..###

""".trimIndent()

val PHLGRNFK = """
#####...#....#..#........####...#####...#....#..######..#....#
#....#..#....#..#.......#....#..#....#..##...#..#.......#...#.
#....#..#....#..#.......#.......#....#..##...#..#.......#..#..
#....#..#....#..#.......#.......#....#..#.#..#..#.......#.#...
#####...######..#.......#.......#####...#.#..#..#####...##....
#.......#....#..#.......#..###..#..#....#..#.#..#.......##....
#.......#....#..#.......#....#..#...#...#..#.#..#.......#.#...
#.......#....#..#.......#....#..#...#...#...##..#.......#..#..
#.......#....#..#.......#...##..#....#..#...##..#.......#...#.
#.......#....#..######...###.#..#....#..#....#..#.......#....#

""".trimIndent()

class TheStarsAlignTest {
    @Test
    fun testPart1Example() = assertEquals(HI, part1(load("example.txt")))

    @Test
    fun testPart1Input() = assertEquals(PHLGRNFK, part1(load("input.txt")))

    @Test
    fun testPart2Example() = assertEquals(3, part2(load("example.txt")))

    @Test
    fun testPart2Input() = assertEquals(10407, part2(load("input.txt")))
}
