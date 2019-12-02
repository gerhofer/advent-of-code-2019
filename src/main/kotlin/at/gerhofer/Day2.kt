package at.gerhofer

import java.lang.IllegalArgumentException

object Day2 {

    fun interpretOpcode(values: List<Int>): List<Int> {
        val result = values.toMutableList()
        for (i in 0 .. values.size step 4) {
            when (result[i]) {
                1 -> result[result[i+3]] = result[result[i+1]] + result[result[i+2]]
                2 -> result[result[i+3]] = result[result[i+1]] * result[result[i+2]]
                99 -> return result
                else -> throw IllegalArgumentException("Something's really wrong")
            }
        }
        return result
    }

}