package at.gerhofer

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import java.util.*

class Day5Test : FunSpec() {

    init {
        test("test advanced op codes 1") {
            val testStack = Stack<Int>()
            testStack.push(5)
            Day5.interpretOpCode(listOf(3,225,1,225,6,6,1100,1,238,225,104,0,1102,40,93,224,1001,224,-3720,224,4,224,102,8,223,223,101,3,224,224,1,224,223,223,1101,56,23,225,1102,64,78,225,1102,14,11,225,1101,84,27,225,1101,7,82,224,1001,224,-89,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,1,35,47,224,1001,224,-140,224,4,224,1002,223,8,223,101,5,224,224,1,224,223,223,1101,75,90,225,101,9,122,224,101,-72,224,224,4,224,1002,223,8,223,101,6,224,224,1,224,223,223,1102,36,63,225,1002,192,29,224,1001,224,-1218,224,4,224,1002,223,8,223,1001,224,7,224,1,223,224,223,102,31,218,224,101,-2046,224,224,4,224,102,8,223,223,101,4,224,224,1,224,223,223,1001,43,38,224,101,-52,224,224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,1102,33,42,225,2,95,40,224,101,-5850,224,224,4,224,1002,223,8,223,1001,224,7,224,1,224,223,223,1102,37,66,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1007,226,677,224,1002,223,2,223,1005,224,329,1001,223,1,223,1007,226,226,224,1002,223,2,223,1006,224,344,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,359,1001,223,1,223,108,677,677,224,1002,223,2,223,1006,224,374,1001,223,1,223,107,677,677,224,1002,223,2,223,1005,224,389,101,1,223,223,8,677,677,224,1002,223,2,223,1005,224,404,1001,223,1,223,108,226,226,224,1002,223,2,223,1005,224,419,101,1,223,223,1008,677,677,224,1002,223,2,223,1005,224,434,101,1,223,223,1008,226,226,224,1002,223,2,223,1005,224,449,101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,464,1001,223,1,223,7,226,226,224,1002,223,2,223,1005,224,479,1001,223,1,223,1007,677,677,224,102,2,223,223,1005,224,494,101,1,223,223,1108,677,226,224,102,2,223,223,1006,224,509,1001,223,1,223,8,677,226,224,102,2,223,223,1005,224,524,1001,223,1,223,1107,226,226,224,102,2,223,223,1006,224,539,1001,223,1,223,1008,226,677,224,1002,223,2,223,1006,224,554,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,569,1001,223,1,223,1108,677,677,224,102,2,223,223,1005,224,584,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,599,1001,223,1,223,1108,226,677,224,102,2,223,223,1006,224,614,101,1,223,223,107,226,677,224,1002,223,2,223,1005,224,629,101,1,223,223,108,226,677,224,1002,223,2,223,1005,224,644,101,1,223,223,8,226,677,224,1002,223,2,223,1005,224,659,1001,223,1,223,107,226,226,224,1002,223,2,223,1006,224,674,101,1,223,223,4,223,99,226),
                    testStack)
        }

        test("test program that checks equality with 8") {
            val testStack = Stack<Int>()
            testStack.push(8)
            Day5.interpretOpCode(listOf(3,9,8,9,10,9,4,9,99,-1,8), testStack) shouldBe listOf(1)


            val testStack2 = Stack<Int>()
            testStack2.push(9)
            Day5.interpretOpCode(listOf(3,9,8,9,10,9,4,9,99,-1,8), testStack2) shouldBe listOf(0)
        }

        test("test program that checks if input is less than 8") {
            val testStack = Stack<Int>()
            testStack.push(6)
            Day5.interpretOpCode(listOf(3,9,7,9,10,9,4,9,99,-1,8), testStack) shouldBe listOf(1)


            val testStack2 = Stack<Int>()
            testStack2.push(9)
            Day5.interpretOpCode(listOf(3,9,7,9,10,9,4,9,99,-1,8), testStack2) shouldBe listOf(0)
        }

        test("test program that checks equality with 8 in immediate mode") {
            val testStack = Stack<Int>()
            testStack.push(8)
            Day5.interpretOpCode(listOf(3,3,1108,-1,8,3,4,3,99), testStack) shouldBe listOf(1)


            val testStack2 = Stack<Int>()
            testStack2.push(1)
            Day5.interpretOpCode(listOf(3,3,1108,-1,8,3,4,3,99), testStack2) shouldBe listOf(0)
        }

        test("test program that checks less than 8 in immediate mode") {
            val testStack = Stack<Int>()
            testStack.push(2)
            Day5.interpretOpCode(listOf(3,3,1107,-1,8,3,4,3,99), testStack) shouldBe listOf(1)


            val testStack2 = Stack<Int>()
            testStack2.push(8)
            Day5.interpretOpCode(listOf(3,3,1107,-1,8,3,4,3,99), testStack2) shouldBe listOf(0)
        }

        test("test input is zero program with jumps") {
            val testStack = Stack<Int>()
            testStack.push(0)
            Day5.interpretOpCode(listOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9), testStack) shouldBe listOf(0)

            val testStack2 = Stack<Int>()
            testStack2.push(-5)
            Day5.interpretOpCode(listOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9), testStack2) shouldBe listOf(1)
        }

        test("test input is zero program with jumps in immediate mode") {
            val testStack = Stack<Int>()
            testStack.push(0)
            Day5.interpretOpCode(listOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1), testStack) shouldBe listOf(0)

            val testStack2 = Stack<Int>()
            testStack2.push(8)
            Day5.interpretOpCode(listOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1), testStack2) shouldBe listOf(1)
        }

    }

}