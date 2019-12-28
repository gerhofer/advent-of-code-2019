package at.gerhofer

import java.io.File
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

enum class Color(val code: Long) {
    WHITE(1),
    BLACK(0);

    companion object {
        fun ofCode(code: Long): Color {
            return values().find { it.code == code }!!
        }
    }
}

data class PaintCell(var color: Color)

enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;
}

object Day11 {

    fun paintIt(filename: String): Map<Point, PaintCell> {
        val paintingArea = mutableMapOf<Point, PaintCell>()
        File(filename)
                .useLines { lines ->
                    val intcode = lines.map { line ->
                        line.split(",")
                                .map { it.toLong() }
                                .toList()
                    }.flatten()
                    var currentPoint = Point(0, 0)
                    var currentDirection = Direction.UP

                    val outputQueue = ArrayBlockingQueue<Long>(1)
                    val inputQueue = ArrayBlockingQueue<Long>(2)
                    inputQueue.put(Color.BLACK.code)
                    val thread = thread {
                        Day9.interpretOpCode(intcode.toList(), inputQueue, outputQueue)
                    }

                    var paintingInstruction = outputQueue.poll(1, TimeUnit.SECONDS)
                    var directionInstruction = outputQueue.poll(1, TimeUnit.SECONDS)

                    while (paintingInstruction != null && directionInstruction != null) {

                        paintingArea[currentPoint] = PaintCell(Color.ofCode(paintingInstruction))
                        currentDirection = turn(currentDirection, directionInstruction)
                        currentPoint = move(currentPoint, currentDirection)

                        inputQueue.put(paintingArea[currentPoint]?.color?.code ?: Color.BLACK.code)

                        paintingInstruction = outputQueue.poll(1, TimeUnit.SECONDS)
                        directionInstruction = outputQueue.poll(1, TimeUnit.SECONDS)
                    }

                    thread.join()

                }
        return paintingArea.toMap()
    }

    fun turn(direction: Direction, code: Long): Direction {
        return when (code) {
            1L -> Direction.values()[(direction.ordinal + 1) % Direction.values().size]
            0L -> Direction.values()[(Direction.values().size + direction.ordinal - 1) % Direction.values().size]
            else -> throw Error("meeeh")
        }
    }

    fun move(point: Point, direction: Direction): Point {
        return when (direction) {
            Direction.UP -> Point(point.x, point.y + 1)
            Direction.DOWN -> Point(point.x, point.y - 1)
            Direction.LEFT -> Point(point.x - 1, point.y)
            Direction.RIGHT -> Point(point.x + 1, point.y)
        }
    }


}