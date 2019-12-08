package at.gerhofer

import java.util.concurrent.ArrayBlockingQueue
import kotlin.concurrent.thread

object Day7 {

    fun findAmplifierOrder(program: List<Int>): Int {
        val results = mutableSetOf<Int>()
        for (a in 5..9) {
            for (b in 5..9) {
                for (c in 5..9) {
                    for (d in 5..9) {
                        for (e in 5..9) {
                            if (setOf(a, b, c, d, e).size != 5) {
                                continue
                            }
                            val eToA = ArrayBlockingQueue<Int>(2)
                            val dToE = ArrayBlockingQueue<Int>(2)
                            val cToD = ArrayBlockingQueue<Int>(2)
                            val bToC = ArrayBlockingQueue<Int>(2)
                            val aToB = ArrayBlockingQueue<Int>(2)
                            eToA.put(a)
                            eToA.put(0)
                            aToB.put(b)
                            bToC.put(c)
                            cToD.put(d)
                            dToE.put(e)
                            val threadA = thread {
                                Day5.interpretOpCode(program, eToA, aToB)
                            }
                            val threadB = thread {
                                Day5.interpretOpCode(program, aToB, bToC)
                            }
                            val threadC = thread {
                                Day5.interpretOpCode(program, bToC, cToD)
                            }
                            val threadD = thread {
                                Day5.interpretOpCode(program, cToD, dToE)
                            }
                            val threadE = thread {
                                Day5.interpretOpCode(program, dToE, eToA)
                            }

                            threadA.join()
                            threadB.join()
                            threadC.join()
                            threadD.join()
                            threadE.join()

                            results.add(eToA.take())
                        }
                    }
                }
            }
        }
        return results.max()!!
    }

}