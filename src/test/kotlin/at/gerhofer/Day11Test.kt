package at.gerhofer

import at.gerhofer.Day11.printIt
import io.kotlintest.specs.FunSpec

class Day11Test : FunSpec() {

    init {

        test("test asteroid point 1") {
            val field = Day11.paintIt("src/test/resources/day11.txt")
            println("my field has a size: ")
            println(field.keys.size)
            printIt(field)
        }

        test("test asteroid point 2 Abl") {
            val field = Day11.paintIt("src/test/resources/day11-abl.txt")
            println("abls field has a size: ")
            println(field.keys.size)
            printIt(field)
        }

    }
}