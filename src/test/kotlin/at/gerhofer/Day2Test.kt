package at.gerhofer

import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.specs.FunSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

class Day2Test : FunSpec() {

    init {

        test("test all the examples") {
            table(
                    headers("input", "expected"),
                    //row(listOf(1,0,0,0,99), listOf(2,0,0,0,99)),
                    //row(listOf(2,3,0,3,99), listOf(2,3,0,6,99)),
                    //row(listOf(2,4,4,5,99,0), listOf(2,4,4,5,99,9801)),
                    row(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
            ).forAll { input: List<Int>, expected: List<Int> ->
                Day2.interpretOpcode(input) shouldContainExactly expected
            }
        }

        test("restore gravity assist program") {
            val result = Day2.interpretOpcode(listOf(1, 12, 2, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 6, 19, 1, 19, 5, 23, 2, 13, 23, 27, 1, 10, 27, 31, 2, 6, 31, 35, 1, 9, 35, 39, 2, 10, 39, 43, 1, 43, 9, 47, 1, 47, 9, 51, 2, 10, 51, 55, 1, 55, 9, 59, 1, 59, 5, 63, 1, 63, 6, 67, 2, 6, 67, 71, 2, 10, 71, 75, 1, 75, 5, 79, 1, 9, 79, 83, 2, 83, 10, 87, 1, 87, 6, 91, 1, 13, 91, 95, 2, 10, 95, 99, 1, 99, 6, 103, 2, 13, 103, 107, 1, 107, 2, 111, 1, 111, 9, 0, 99, 2, 14, 0, 0))
            println(result)
        }

        test("find noun and verb") {
            for (noun in 1..100) {
                for (verb in 1..100) {
                    val result = Day2.interpretOpcode(listOf(1, noun, verb, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 6, 19, 1, 19, 5, 23, 2, 13, 23, 27, 1, 10, 27, 31, 2, 6, 31, 35, 1, 9, 35, 39, 2, 10, 39, 43, 1, 43, 9, 47, 1, 47, 9, 51, 2, 10, 51, 55, 1, 55, 9, 59, 1, 59, 5, 63, 1, 63, 6, 67, 2, 6, 67, 71, 2, 10, 71, 75, 1, 75, 5, 79, 1, 9, 79, 83, 2, 83, 10, 87, 1, 87, 6, 91, 1, 13, 91, 95, 2, 10, 95, 99, 1, 99, 6, 103, 2, 13, 103, 107, 1, 107, 2, 111, 1, 111, 9, 0, 99, 2, 14, 0, 0))
                    if (result[0] == 19690720) {
                        println("Noun ${noun} and verb ${verb}")
                        break
                    }
                }
            }
        }

    }

}