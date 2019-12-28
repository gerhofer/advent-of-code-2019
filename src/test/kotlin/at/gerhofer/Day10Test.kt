package at.gerhofer

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class Day10Test : FunSpec() {

    init {

        test("test asteroid point 1") {
            Day10.getBestAsteroid("src/test/resources/day10-1.txt") shouldBe 8
        }

        test("test asteroid point 2") {
            Day10.getBestAsteroid("src/test/resources/day10-2.txt") shouldBe 33
        }

        test("test asteroid point 3") {
            Day10.getBestAsteroid("src/test/resources/day10-3.txt") shouldBe 35
        }

        test("test asteroid point 4") {
            Day10.getBestAsteroid("src/test/resources/day10-4.txt") shouldBe 41
        }

        test("test asteroid point 5") {
            Day10.getBestAsteroid("src/test/resources/day10-5.txt") shouldBe 210
        }

        test("test asteroid point real") {
            Day10.getBestAsteroid("src/test/resources/day10.txt") shouldBe 210
        }
    }

}