package at.gerhofer

import java.io.File
import kotlin.math.abs
import kotlin.math.sign

enum class Sight {
    ASTEROID,
    BLOCKED,
    EMPTY
}

data class Location(
        val x: Int,
        val y: Int
)

data class LocationWithObject(
        val location: Location,
        var sight: Sight
)

object Day10 {

    fun getBestAsteroid(filename: String): Int {
        File(filename)
                .useLines { lines ->
                    var y = 0
                    val field = lines.map { line ->
                        var x = 0
                        val list = line.map { character ->
                            val locationPoint = when (character) {
                                '.' -> LocationWithObject(Location(x, y), Sight.EMPTY)
                                '#' -> LocationWithObject(Location(x, y), Sight.ASTEROID)
                                else -> throw IllegalArgumentException("invalid character")
                            }
                            x++
                            locationPoint
                        }.toList()
                        y++
                        list
                    }.toList()

                    val viewPointsWithCounts = calculateViewPoints(field)
                    return viewPointsWithCounts.maxBy { it.value }!!.value
                }
    }

    fun calculateViewPoints(field: List<List<LocationWithObject>>): Map<Location, Int> {
        val viewPointsWithCounts = mutableMapOf<Location, Int>()
        for (y in 0..(field.size - 1)) {
            for (x in 0..(field[0].size - 1)) {
                val location = Location(x, y)
                if (field[y][x].sight == Sight.ASTEROID) {
                    viewPointsWithCounts[location] = getVisibleAsteroids(field, location)
                }
            }
        }
        return viewPointsWithCounts
    }

    fun getVisibleAsteroids(field: List<List<LocationWithObject>>, observatoryLocation: Location): Int {
        val otherAsteroids = fieldCopy(field).flatten()
                .filter { it.sight == Sight.ASTEROID && it.location != observatoryLocation }
                .sortedBy { abs(it.location.x - observatoryLocation.x) + abs(it.location.y - observatoryLocation.y) }
                .toList()
        for (asteroid in otherAsteroids) {
            if (asteroid.sight == Sight.BLOCKED) {
                continue
            }
            otherAsteroids.filter { it != asteroid }
                    .filter { it.sight == Sight.ASTEROID }
                    .filter {
                        val xRelatio = (it.location.x - observatoryLocation.x).toDouble() / (asteroid.location.x - observatoryLocation.x).toDouble()
                        val yRelatio = (it.location.y - observatoryLocation.y).toDouble() / (asteroid.location.y - observatoryLocation.y).toDouble()
                        (xRelatio == yRelatio && xRelatio > 0.0 && yRelatio > 0.0)|| isVertical(observatoryLocation, asteroid.location, it.location) || isHorizontal(observatoryLocation, asteroid.location, it.location)//&& xRelatio > 0.0 && yRelatio > 0.0
                    }
                    .map {
                        println("${it} is blocked by ${asteroid} seen by ${observatoryLocation}")
                        it.sight = Sight.BLOCKED
                    }
        }
        return otherAsteroids.filter { it.sight == Sight.ASTEROID }.count()
    }

    fun isHorizontal(observatoryLocation: Location, location: Location, possiblyblocked: Location): Boolean {
        return observatoryLocation.x == location.x && location.x == possiblyblocked.x &&
                sign(observatoryLocation.y.toDouble() - location.y.toDouble()) == sign(observatoryLocation.y.toDouble() - possiblyblocked.y.toDouble())
    }

    fun isVertical(observatoryLocation: Location, location: Location, possiblyblocked: Location): Boolean {
        return observatoryLocation.y == location.y && location.y == possiblyblocked.y &&
                sign(observatoryLocation.x.toDouble() - location.x.toDouble()) == sign(observatoryLocation.x.toDouble() - possiblyblocked.x.toDouble())
    }

    fun fieldCopy(field: List<List<LocationWithObject>>): List<List<LocationWithObject>> {
        val fieldCopy = mutableListOf<List<LocationWithObject>>()
        field.forEach {
            val row = mutableListOf<LocationWithObject>()
            it.forEach { loc -> row.add(loc.copy()) }
            fieldCopy.add(row)
        }
        return fieldCopy
    }

}