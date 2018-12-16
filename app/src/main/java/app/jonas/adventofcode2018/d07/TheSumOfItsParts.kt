package app.jonas.adventofcode2018.d07

import java.io.File

val pattern = Regex("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.")

class InstructionsIterator(private var steps: MutableMap<Char, MutableSet<Char>>) : Iterator<List<Char>> {
    override fun hasNext() = steps.isNotEmpty()

    override fun next() = steps.filter { it.value.isEmpty() }.keys.sorted()

    fun takeStep(step: Char) = steps.remove(step)

    fun finishStep(step: Char) = steps.forEach { it.value.remove(step) }
}

class Instructions(file: File) : Iterable<List<Char>> {
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

fun part1(file: File): String {
    var res = ""
    val iterator = Instructions(file).iterator()
    while (iterator.hasNext()) {
        val step = iterator.next().first()
        iterator.takeStep(step)
        iterator.finishStep(step)
        res += step
    }
    return res
}

class Worker(val id: Int, private val additionalStepDuration: Int) {
    private var workingTill: Int? = null
    var step: Char? = null
        private set

    fun workOn(step: Char, second: Int) {
        // println("${"$second".padStart(3, ' ')} Worker $id takes step $step!")
        this.step = step
        workingTill = second + step.toInt() - 64 + additionalStepDuration - 1
    }

    fun checkWorkStatus(second: Int): Char? {
        workingTill?.let {
            if (second >= it) {
                workingTill = null
                // println("${"$second".padStart(3, ' ')} Worker $id finishes step $step!")
                return step.also { step = null }
            }
        }
        return null
    }

    fun timeLeft(second: Int) = workingTill?.let { it - second }
}

fun part2(file: File, workerCount: Int, additionalStepDuration: Int): Int {
    val iterator = Instructions(file).iterator()
    val workers = Array(workerCount) { i -> Worker(i, additionalStepDuration) }
    var second = 0
    while (true) {
        val nextSteps = iterator.next()
        val (idle, working) = workers.partition { it.step == null }
        nextSteps.zip(idle).forEach { (step, worker) ->
            iterator.takeStep(step)
            worker.workOn(step, second)
        }
        var someoneFinished = false
        working.forEach { worker ->
            worker.checkWorkStatus(second)?.let { finishedStep ->
                someoneFinished = true
                iterator.finishStep(finishedStep)
            }
        }
        second += if (someoneFinished) {
            1
        } else {
            val minWorkingTime = workers.mapNotNull { it.timeLeft(second) }.min()
            minWorkingTime ?: 1 // Nobody is working → check next second
        }
        if (!iterator.hasNext() && someoneFinished) {
            return second
        }
    }
}
