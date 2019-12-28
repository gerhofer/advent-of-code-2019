package at.gerhofer

import java.util.concurrent.BlockingQueue

const val POSITION_MODE = 0
const val IMMEDIATE_MODE = 1

object Day5 {

    fun interpretOpCode(values: List<Int>, input: BlockingQueue<Int>, output: BlockingQueue<Int>): List<Int> {
        val result = values.toMutableList()
        val outputs = mutableListOf<Int>()
        var i = 0
        while (i < values.size) {
            val opcode = result[i] % 100
            val parameterCodes = result[i] / 100
            when (opcode) {
                1 -> {
                    result[thirdParameter(parameterCodes, i, result)] = result[firstParameter(parameterCodes, i, result)] + result[secondParameter(parameterCodes, i, result)]
                    i += 4
                }
                2 -> {
                    result[thirdParameter(parameterCodes, i, result)] = result[firstParameter(parameterCodes, i, result)] * result[secondParameter(parameterCodes, i, result)]
                    i += 4
                }
                3 -> {
                    result[result[i + 1]] = input.take()
                    i += 2
                }
                4 -> {
                    val currentOutput = result[result[i + 1]]
                    output.put(currentOutput)
                    outputs.add(currentOutput)
                    println(currentOutput)
                    i += 2
                }
                5 -> {
                    if (result[firstParameter(parameterCodes, i, result)] != 0) {
                        i = result[secondParameter(parameterCodes, i, result)]
                    } else {
                        i += 3
                    }
                }
                6 -> {
                    if (result[firstParameter(parameterCodes, i, result)] == 0) {
                        i = result[secondParameter(parameterCodes, i, result)]
                    } else {
                        i += 3
                    }
                }
                7 -> {
                    result[thirdParameter(parameterCodes, i, result)] = if (result[firstParameter(parameterCodes, i, result)] < result[secondParameter(parameterCodes, i, result)]) {
                        1
                    } else {
                        0
                    }
                    i += 4
                }
                8 -> {
                    result[thirdParameter(parameterCodes, i, result)] = if (result[firstParameter(parameterCodes, i, result)] == result[secondParameter(parameterCodes, i, result)]) {
                        1
                    } else {
                        0
                    }
                    i += 4
                }
                99 -> return outputs.toList()
                else -> throw IllegalArgumentException("Something's really wrong got op code $opcode")
            }
        }
        return outputs.toList()
    }

    fun firstParameter(parameterModes: Int, idx: Int, result: MutableList<Int>) : Int {
        val mode1 = parameterModes % 10
        if (mode1 == IMMEDIATE_MODE) {
            return idx + 1
        } else if (mode1 == POSITION_MODE) {
            return result[idx + 1]
        }
        throw java.lang.IllegalArgumentException("very wrong")
    }

    fun secondParameter(parameterModes: Int, idx: Int, result: MutableList<Int>) : Int {
        val mode2 = parameterModes / 10 % 10
        if (mode2 == IMMEDIATE_MODE) {
            return idx + 2
        } else if (mode2 == POSITION_MODE) {
            return result[idx + 2]
        }
        throw java.lang.IllegalArgumentException("very wrong")

    }

    fun thirdParameter(parameterModes: Int, idx: Int, result: MutableList<Int>) : Int {
        val mode3 = parameterModes / 100 % 10
        if (mode3 == IMMEDIATE_MODE) {
            return idx + 3
        } else if (mode3 == POSITION_MODE) {
            return result[idx + 3]
        }
        throw java.lang.IllegalArgumentException("very wrong")

    }

}