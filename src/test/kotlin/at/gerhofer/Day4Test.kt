package at.gerhofer

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class Day4Test : FunSpec() {

    init {
        test("test number of valid numbers in range") {
            println(Day4.getCountOfValidNumbersInRange(134564, 585159))
        }

        test("test is valid") {
            Day4.isValid(111111) shouldBe true
            Day4.isValid(223450) shouldBe false
            Day4.isValid(123789) shouldBe false
        }

        test("test number of valid numbers in range 2") {
            println(Day4.getValidNumbersInRange2(134564, 585159).count())
        }

        test("test is valid2") {
            Day4.isValid2(112233) shouldBe true
            Day4.isValid2(123444) shouldBe false
            Day4.isValid2(111122) shouldBe true
            Day4.isValid2(135556) shouldBe false
        }


    }

}