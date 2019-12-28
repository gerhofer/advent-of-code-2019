package at.gerhofer

import io.kotlintest.specs.FunSpec

class Day11Test : FunSpec() {

    init {

        test("test asteroid point 1") {
            val field = Day11.paintIt("src/test/resources/day11.txt")
            println("my field has a size: ")
            println(field.keys.size)
        }

    }
}