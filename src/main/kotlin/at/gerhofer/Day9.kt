package at.gerhofer

import java.util.concurrent.BlockingQueue

const val POSITION_MODE1 = 0L
const val IMMEDIATE_MODE1 = 1L
const val RELATIVE_MODE = 2L

object Day9 {

    fun interpretOpCode(values: List<Long>, input: BlockingQueue<Long>, output: BlockingQueue<Long>): List<Long> {
        val result = values.toMutableList()
        val outputs = mutableListOf<Long>()
        var relativeMode = 0L
        var i = 0L
        while (i < values.size) {
            val opcode = result[i.toInt()] % 100
            val parameterCodes = result[i.toInt()] / 100
            when (opcode.toInt()) {
                1 -> {
                    verifySize(result,
                            listOf(thirdParameter(parameterCodes, i, result, relativeMode).toInt(), secondParameter(parameterCodes, i, result, relativeMode).toInt(), firstParameter(parameterCodes, i, result, relativeMode).toInt())
                    )
                    result[thirdParameter(parameterCodes, i, result, relativeMode).toInt()] = result[firstParameter(parameterCodes, i, result, relativeMode).toInt()] + result[secondParameter(parameterCodes, i, result, relativeMode).toInt()]
                    i += 4
                }
                2 -> {
                    verifySize(result,
                            listOf(thirdParameter(parameterCodes, i, result, relativeMode).toInt(), secondParameter(parameterCodes, i, result, relativeMode).toInt(), firstParameter(parameterCodes, i, result, relativeMode).toInt())
                    )
                    result[thirdParameter(parameterCodes, i, result, relativeMode).toInt()] = result[firstParameter(parameterCodes, i, result, relativeMode).toInt()] * result[secondParameter(parameterCodes, i, result, relativeMode).toInt()]
                    i += 4
                }
                3 -> {
                    val parameter = firstParameter(parameterCodes, i, result, relativeMode)
                    verifySize(result, listOf(parameter.toInt()))
                    verifySize(result, listOf(result[parameter.toInt()].toInt()))
                    result[parameter.toInt()] = input.take()
                    i += 2
                }
                4 -> {
                    verifySize(result, listOf(firstParameter(parameterCodes, i, result, relativeMode).toInt()))
                    val currentOutput = result[firstParameter(parameterCodes, i, result, relativeMode).toInt()]
                    output.put(currentOutput)
                    outputs.add(currentOutput)
                    i += 2
                }
                5 -> {
                    verifySize(result, listOf(firstParameter(parameterCodes, i, result, relativeMode).toInt(), secondParameter(parameterCodes, i, result, relativeMode).toInt()))

                    if (result[firstParameter(parameterCodes, i, result, relativeMode).toInt()] != 0L) {
                        i = result[secondParameter(parameterCodes, i, result, relativeMode).toInt()]
                    } else {
                        i += 3
                    }
                }
                6 -> {
                    verifySize(result, listOf(firstParameter(parameterCodes, i, result, relativeMode).toInt(), secondParameter(parameterCodes, i, result, relativeMode).toInt()))

                    if (result[firstParameter(parameterCodes, i, result, relativeMode).toInt()] == 0L) {
                        i = result[secondParameter(parameterCodes, i, result, relativeMode).toInt()]
                    } else {
                        i += 3
                    }
                }
                7 -> {
                    verifySize(result, listOf(firstParameter(parameterCodes, i, result, relativeMode).toInt(),
                            secondParameter(parameterCodes, i, result, relativeMode).toInt(),
                            thirdParameter(parameterCodes, i, result, relativeMode).toInt()))

                    result[thirdParameter(parameterCodes, i, result, relativeMode).toInt()] = if (result[firstParameter(parameterCodes, i, result, relativeMode).toInt()] < result[secondParameter(parameterCodes, i, result, relativeMode).toInt()]) {
                        1
                    } else {
                        0
                    }
                    i += 4
                }
                8 -> {
                    verifySize(result, listOf(firstParameter(parameterCodes, i, result, relativeMode).toInt(),
                            secondParameter(parameterCodes, i, result, relativeMode).toInt(),
                            thirdParameter(parameterCodes, i, result, relativeMode).toInt()))

                    result[thirdParameter(parameterCodes, i, result, relativeMode).toInt()] = if (result[firstParameter(parameterCodes, i, result, relativeMode).toInt()] == result[secondParameter(parameterCodes, i, result, relativeMode).toInt()]) {
                        1
                    } else {
                        0
                    }
                    i += 4
                }
                9 -> {
                    verifySize(result, listOf(firstParameter(parameterCodes, i, result, relativeMode).toInt()))
                    relativeMode += result[firstParameter(parameterCodes, i, result, relativeMode).toInt()]
                    i += 2
                }
                99 -> return outputs.toList()
                else -> throw IllegalArgumentException("Something's really wrong got op code $opcode")
            }
        }
        return outputs.toList()
    }

    fun verifySize(result: MutableList<Long>, indices: List<Int>) {
        while (result.size <= indices.max()!!) {
            result.add(0L)
        }
    }

    fun firstParameter(parameterModes: Long, idx: Long, result: MutableList<Long>, relativeMode: Long): Long {
        val mode1 = parameterModes % 10
        if (mode1 == IMMEDIATE_MODE1) {
            return idx + 1
        } else if (mode1 == POSITION_MODE1) {
            return result[idx.toInt() + 1]
        } else if (mode1 == RELATIVE_MODE) {
            return relativeMode + result[idx.toInt() + 1]
        }
        throw java.lang.IllegalArgumentException("very wrong")
    }

    fun secondParameter(parameterModes: Long, idx: Long, result: MutableList<Long>, relativeMode: Long): Long {
        val mode2 = parameterModes / 10 % 10
        if (mode2 == IMMEDIATE_MODE1) {
            return idx + 2
        } else if (mode2 == POSITION_MODE1) {
            return result[idx.toInt() + 2]
        } else if (mode2 == RELATIVE_MODE) {
            return relativeMode + result[idx.toInt() + 2]
        }
        throw java.lang.IllegalArgumentException("very wrong")

    }

    fun thirdParameter(parameterModes: Long, idx: Long, result: MutableList<Long>, relativeMode: Long): Long {
        val mode3 = parameterModes / 100 % 10
        if (mode3 == IMMEDIATE_MODE1) {
            return idx + 3
        } else if (mode3 == POSITION_MODE1) {
            return result[idx.toInt() + 3]
        } else if (mode3 == RELATIVE_MODE) {
            return relativeMode + result[idx.toInt() + 3]
        }
        throw java.lang.IllegalArgumentException("very wrong")

    }

}