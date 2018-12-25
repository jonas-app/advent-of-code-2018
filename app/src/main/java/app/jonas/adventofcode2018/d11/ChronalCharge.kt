package app.jonas.adventofcode2018.d11

const val SIZE = 300

typealias Plane = List<Pair<Int, List<Pair<Int, Int>>>>

fun calculatePowerLevel(x: Int, y: Int, serialNumber: Int): Int {
    val rackId = x + 10L
    val powerLevel = (rackId * y + serialNumber) * rackId
    val digit = powerLevel / 100 % 10
    return (digit - 5).toInt()
}

fun getPowerLevelPlane(serialNumber: Int): Plane {
    return List(SIZE) { y -> y + 1 to List(SIZE) { x -> x + 1 to calculatePowerLevel(x + 1, y + 1, serialNumber) } }
}

fun Plane.getLargestTotalPower(size: Int): Pair<String, Int> {
    val rowWindowed = this.map { (y, row) ->
        y to row.windowed(size) { xWindow ->
            val (x) = xWindow.first()
            x to xWindow.fold(0) { acc, (_, value) -> acc + value }
        }
    }
    return rowWindowed.windowed(size) { yWindow ->
        val (y, firstRow) = yWindow.first()
        firstRow.mapIndexed { i, (x) -> "$x,$y" to yWindow.fold(0) { acc, (_, row) -> acc + row[i].second } }
    }.flatten().maxBy { (_, power) -> power } ?: throw Error("No maximum found!")
}

fun part1(serialNumber: Int): String {
    val plane = getPowerLevelPlane(serialNumber)
    val (coordinates) = plane.getLargestTotalPower(3)
    return coordinates
}

fun part2(serialNumber: Int): String {
    val plane = getPowerLevelPlane(serialNumber)
    val largestTotalPowers = (1..300).map { size -> size to plane.getLargestTotalPower(size) }
    val (size, largestTotalPower) = largestTotalPowers.maxBy { (_, largestTotalPower) -> largestTotalPower.second }
        ?: throw Error("No maximum found!")
    val (coordinates) = largestTotalPower
    return "$coordinates,$size"
}
