package at.gerhofer

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class Day6Test : FunSpec() {

    init {

        test("test orbits of example") {
            val orbits = Day6.parseOrbits("src/test/resources/day6-test.txt")
            val allOrbits = orbits.map { Day6.flatOrbitList(it) }.flatten()
            println(allOrbits.map { it.name + " has parent " + it.parent?.name })
            Day6.countAllEdges(allOrbits.toList()) shouldBe 42
        }

        test("test orbits for first star") {
            val orbits = Day6.parseOrbits("src/test/resources/day6.txt")
            val allOrbits = orbits.map { Day6.flatOrbitList(it) }.flatten()
            println(Day6.countAllEdges(allOrbits))
        }

    }

}