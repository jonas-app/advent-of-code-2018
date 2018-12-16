package app.jonas.adventofcode2018.d07

import java.io.File

val pattern = Regex("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.")

class InstructionsIterator(private var steps: MutableMap<Char, MutableSet<Char>>) : Iterator<Char> {
    override fun hasNext() = steps.isNotEmpty()

    override fun next(): Char {
        val prerequisitesDone = steps.filter { it.value.isEmpty() }.toSortedMap()
        val step = prerequisitesDone.firstKey()
        steps.remove(step)
        steps.forEach { it.value.remove(step) }
        return step
    }
}

class Instructions(file: File) : Iterable<Char> {
    override fun iterator() = InstructionsIterator(steps.toMutableMap())

    private val steps = file.readLines().fold(mutableMapOf<Char, MutableSet<Char>>()) { steps, line ->
        pattern.matchEntire(line)?.groupValues?.drop(1)?.map(String::single)?.let { (prerequisite, step) ->
            if (steps[prerequisite] == null) {
                steps[prerequisite] = mutableSetOf()
            }
            if (steps[step] == null) {
                steps[step] = mutableSetOf(prerequisite)
            } else {
                steps[step]?.add(prerequisite)
            }
        }
        steps
    }
}

fun part1(file: File) = Instructions(file).fold("") { acc, step -> acc + step }

fun part2(file: File): Int {
    return 1
}
