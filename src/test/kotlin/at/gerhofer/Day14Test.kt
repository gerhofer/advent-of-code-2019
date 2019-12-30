package at.gerhofer

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class Day14Test : FunSpec() {

    init {

        test("test fuel requirement 1") {
            Day14.reset()
            Day14.calculateOreForFuel("src/test/resources/day12-test.txt") shouldBe 31

            Day14.reset()
            Day14.calculateOreForFuel("src/test/resources/day12-1.txt") shouldBe 165
        }

        test("test fuel requirement 2") {
            Day14.reset()
            Day14.calculateOreForFuel("src/test/resources/day12-2.txt") shouldBe 13312
        }

        test("test fuel requirement 3") {
            Day14.reset()

            Day14.calculateOreForFuel("src/test/resources/day12-3.txt") shouldBe 180697
        }

        test("test fuel requirement 4") {
            Day14.reset()
            Day14.calculateOreForFuel("src/test/resources/day12-4.txt") shouldBe 2210736
        }

//        test("test all fuel requirement 2") {
//            Day14.reset()
//            Day14.produceAllTheFuel("src/test/resources/day12-2.txt") shouldBe 82892753
//        }

  //      test("test all fuel requirement 3") {
  //          Day14.reset()
//
  //          Day14.produceAllTheFuel("src/test/resources/day12-3.txt") shouldBe 5586022
  //      }
//
  //      test("test all fuel requirement 4") {
  //          Day14.reset()
  //          Day14.produceAllTheFuel("src/test/resources/day12-4.txt") shouldBe 460664
  //      }

        test("test fuel requirement pia") {
            Day14.reset()
            println(Day14.produceAllTheFuel("src/test/resources/day14-pia.txt"))
        }

        test("test fuel requirement abl") {
            Day14.reset()
            println(Day14.produceAllTheFuel("src/test/resources/day14-abl.txt"))
        }

    }
}