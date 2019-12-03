package at.gerhofer

import kotlin.math.abs

data class Point(
        val x: Int,
        val y: Int
)

data class Movement(
        val direction: String,
        val length: Int
)

data class Wire(
        val movements: List<Movement>
)

object Day3 {

    fun getClosestPoint(wires: List<String>): Point {
        return getCrossingPoints(wires).filter { it.x != 0 && it.y != 0 }
                .minBy { abs(it.x) + abs(it.y) }!!
    }

    fun getClosestStepSize(wires: List<String>): Int {
        val wireMovements = getWireMovements(wires)
        val crossingPoints = getCrossingPoints(wires)

        return crossingPoints.filter { it.x!= 0 && it.y != 0 }
                .map {signalDistance(wireMovements, it) }
                .min()!!
    }

    fun signalDistance(wireMovements: List<Wire>, point: Point) : Int{
        var stepCount = 0
        for (wire in wireMovements) {
            var currentPoint = Point(0, 0)
            for (movement in wire.movements) {
                val crossedPoints = getCrossedPoints(movement, currentPoint)
                if (crossedPoints.contains(point)) {
                    val idxPoint = crossedPoints.indexOf(point)
                    stepCount += idxPoint
                    break
                } else {
                    stepCount += (crossedPoints.size - 1)
                }
                currentPoint = crossedPoints.last()
            }
        }
        return stepCount
    }

    fun getCrossingPoints(wires: List<String>): List<Point> {
        val wireMovements = getWireMovements(wires)
        val fieldMapping = mutableMapOf<Point, MutableSet<Int>>()
        var index = 1
        for (wire in wireMovements) {
            var currentPoint = Point(0, 0)
            for (movement in wire.movements) {
                val crossedPoints = getCrossedPoints(movement, currentPoint)
                currentPoint = crossedPoints.last()
                crossedPoints.forEach {
                    if (fieldMapping.containsKey(it)) {
                        fieldMapping[it]!!.add(index)
                    } else {
                        fieldMapping[it] = mutableSetOf(index)
                    }
                }
            }
            index++
        }

        return fieldMapping.filter { entry -> entry.value.size > 1 }
                .keys
                .toList()
    }

    private fun getCrossedPoints(movement: Movement, currentPoint: Point): List<Point> {
        return when (movement.direction) {
            "R" -> right(currentPoint, movement.length)
            "L" -> left(currentPoint, movement.length)
            "U" -> up(currentPoint, movement.length)
            "D" -> down(currentPoint, movement.length)
            else -> listOf()
        }
    }

    private fun getWireMovements(wires: List<String>) = wires.map { it.split(",") }
            .map { movements -> Wire(movements.map { movement -> Movement(movement.substring(0, 1), movement.substring(1).toInt()) }) }

    private fun right(startingPoint: Point, length: Int): List<Point> {
        return (0..length)
                .map { idx -> Point(startingPoint.x + idx, startingPoint.y) }
                .toList()
    }

    private fun left(startingPoint: Point, length: Int): List<Point> {
        return (0..length)
                .map { idx -> Point(startingPoint.x - idx, startingPoint.y) }
                .toList()
    }

    private fun up(startingPoint: Point, length: Int): List<Point> {
        return (0..length)
                .map { idx -> Point(startingPoint.x, startingPoint.y + idx) }
                .toList()
    }

    private fun down(startingPoint: Point, length: Int): List<Point> {
        return (0..length)
                .map { idx -> Point(startingPoint.x, startingPoint.y - idx) }
                .toList()
    }
}