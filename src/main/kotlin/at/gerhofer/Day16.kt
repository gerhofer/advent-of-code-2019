package at.gerhofer

import kotlin.math.absoluteValue

object Day16 {

    val initialPattern = listOf(0, 1, 0, -1)

    fun calculatePhaseFor100000(phase: Int, input: List<Int>): List<Int> {
        var currentPhase = 0
        var mutableInput = input
        while (currentPhase < phase) {
            mutableInput = nextPhaseTimes10000(mutableInput)
            currentPhase++
        }
        return mutableInput.take(8)
    }

    fun calculatePhase(phase: Int, input: List<Int>): List<Int> {
        var currentPhase = 0
        var mutableInput = input
        while (currentPhase < phase) {
            mutableInput = nextPhase(mutableInput)
            currentPhase++
        }
        return mutableInput.take(8)
    }

    fun nextPhase(input: List<Int>): List<Int> {
        return (input.indices).map { index ->
            val pattern = initialPattern.flatMap { patternValue -> List(index + 1 ) { patternValue } }
            input.mapIndexed { idx, it ->
                it * pattern[(idx + 1) % pattern.size]
            }.sum().absoluteValue % 10
        }
    }

    fun nextPhaseTimes10000(input: List<Int>): List<Int> {
        return (input.indices).map { index ->
            val pattern = initialPattern.flatMap { patternValue -> List(index + 1 ) { patternValue } }
            (0L until input.size*10000L).map{ idx ->
                input[(idx % input.size).toInt()] * pattern[((idx + 1) % pattern.size).toInt()]
            }.sum().absoluteValue % 10
        }
    }

}