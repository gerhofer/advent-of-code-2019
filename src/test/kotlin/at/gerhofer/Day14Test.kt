package at.gerhofer

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class Day14Test : FunSpec() {

    init {

        test("test fuel requirement 1") {
            Day14.calculateOreForFuel("src/test/resources/day12-1.txt") shouldBe 165
        }

        test("test fuel requirement 2") {
            Day14.calculateOreForFuel("src/test/resources/day12-2.txt") shouldBe 13312

        }

        test("test fuel requirement 3") {
            Day14.calculateOreForFuel("src/test/resources/day12-3.txt") shouldBe 180697

        }

        test("test fuel requirement 4") {
            Day14.calculateOreForFuel("src/test/resources/day12-4.txt") shouldBe 2210736
        }

    }
}