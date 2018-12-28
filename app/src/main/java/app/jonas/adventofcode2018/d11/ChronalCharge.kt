package app.jonas.adventofcode2018.d11

const val SIZE = 300

typealias Plane = List<List<Int>>

fun calculatePowerLevel(x: Int, y: Int, serialNumber: Int): Int {
    val rackId = x + 10L
    val powerLevel = (rackId * y + serialNumber) * rackId
    val digit = powerLevel / 100 % 10
    return (digit - 5).toInt()
}

// Initializes a 2d Array with the calculated power levels
fun getPowerLevelPlane(serialNumber: Int): Plane {
    return List(SIZE) { y ->
        List(SIZE) { x ->
            calculatePowerLevel(x + 1, y + 1, serialNumber)
        }
    }
}

private fun Plane.sumRowsWindowed(size: Int): Plane {
    return map { row ->
        row.windowed(size) { xWindow ->
            xWindow.fold(0) { sum, powerLevel -> sum + powerLevel }
        }
    }
}

private fun Plane.sumColumnsWindowed(size: Int, step: Int = 1): Plane {
    return windowed(size, step) { windowedRows ->
        val rowSize = windowedRows.first().size
        List(rowSize) { i ->
            windowedRows.fold(0) { sum, row -> sum + row[i] }
        }
    }
}

private fun Plane.sumWindowed(size: Int): Plane {
    val rowsWindowedSums = sumRowsWindowed(size)
    return rowsWindowedSums.sumColumnsWindowed(size)
}

fun Plane.getLargestTotalPower(): Pair<String, Int> {
    return foldIndexed("" to 0) { y, max, row ->
        row.foldIndexed(max) { x, rowMax, value ->
            val (_, currentMax) = rowMax
            if (currentMax >= value) {
                rowMax
            } else {
                val coordinates = "${x + 1},${y + 1}"
                coordinates to value
            }
        }
    }
}

fun part1(serialNumber: Int): String {
    val plane = getPowerLevelPlane(serialNumber)
    val windowedSums = plane.sumWindowed(3)
    val (coordinates) = windowedSums.getLargestTotalPower()
    return coordinates
}

fun part2(serialNumber: Int): String {
    val plane = getPowerLevelPlane(serialNumber)
    val largestTotalPowers = (1..300).map { size -> size to plane.sumWindowed(size).getLargestTotalPower() }
    val (size, largestTotalPower) = largestTotalPowers.maxBy { (_, largestTotalPower) -> largestTotalPower.second }
        ?: throw Error("No maximum found!")
    val (coordinates) = largestTotalPower
    return "$coordinates,$size"
}
