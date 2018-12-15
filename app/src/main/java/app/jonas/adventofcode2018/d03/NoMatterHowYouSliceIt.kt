package app.jonas.adventofcode2018.d03

import java.io.File

val pattern = Regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)")

fun buildFabric(file: File, writeCell: (value: Int, id: Int) -> Int): Array<IntArray> {
    var fabric = Array(1) {IntArray(1)}
    file.readLines().forEach {
        pattern.matchEntire(it)?.groupValues?.drop(1)?.map(String::toInt)?.let { (id, left, top, width, height) ->
            // println("id: $id, left: $left, top: $top, width: $width, height: $height")
            val right = left + width
            val bottom = top + height
            val max = maxOf(fabric[0].size, right)
            fabric = Array(maxOf(fabric.size, bottom)) {rowI ->
                val row = fabric.getOrNull(rowI)?.copyOf(max) ?: IntArray(max)
                if (rowI in top until bottom) for (cell in left until right) row[cell] = writeCell(row[cell], id)
                row
            }
        }
    }
    return fabric
}

fun part1(file: File): Int {
    val fabric = buildFabric(file) {value, _ -> value + 1 }
    //fabric.forEach { println(it.joinToString(" ")) }
    return fabric.fold(0) {acc, row -> acc + row.fold(0) {accRow, cell -> if (cell > 1) accRow + 1 else accRow}}
}

fun part2(file: File): Int {
    val overlap = mutableSetOf<Int>()
    var lastId = 1;
    buildFabric(file) {value, id ->
        if (value != 0) {
            overlap.add(value)
            overlap.add(id)
        }
        lastId = id
        id
    }
    return (1..lastId).minus(overlap).single()
}