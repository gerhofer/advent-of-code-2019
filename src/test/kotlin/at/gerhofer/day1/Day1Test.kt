package at.gerhofer.day1

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table
import java.io.File

class Day1Test : FunSpec() {

    init {

        test("mass example 1") {
            table(
                    headers("input", "expected"),
                    row(12, 2),
                    row(14, 2),
                    row(1969, 654),
                    row(100756, 33583)
            ).forAll { input, expected ->
                Day1.calculateFuelRequirements(input) shouldBe expected
            }
        }

        test("input day 1") {
            val sum = File("src/test/resources/day1.txt")
                    .useLines { lines ->
                        lines.map { Day1.calculateFuelRequirements(it.toInt()) }
                                .sum()
                    }
            println(sum)
        }

        test("recursive mass example 1") {
            table(
                    headers("input", "expected"),
                    row(14, 2),
                    row(1969, 966),
                    row(100756, 50346)
            ).forAll { input, expected ->
                Day1.calculateFuelRequirementsRecursively(input) shouldBe expected
            }
        }

        test("input day 1 - 2") {
            val sum = File("src/test/resources/day1.txt")
                    .useLines { lines ->
                        lines.map { Day1.calculateFuelRequirementsRecursively(it.toInt()) }
                                .sum()
                    }
            println(sum)
        }

    }

}
