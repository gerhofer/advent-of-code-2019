package at.gerhofer

import java.io.File

data class Orbit(
        val name: String,
        val objectsInOrbit: MutableList<Orbit>,
        var parent: Orbit?
) {

    override fun hashCode(): Int {
        return name.hashCode() + parent.hashCode()
    }

    override fun toString(): String {
        return name + "[" + parent?.name + "]"
    }
}

object Day6 {

    fun parseOrbits(fileName: String): List<Orbit> {
        var withoutParent: MutableList<Orbit> = mutableListOf()
        File(fileName)
                .useLines { lines ->
                    lines.toList().forEach { line ->
                        println(line)
                        val names = line.split(")")
                        var parentOrbit = findOrbit(withoutParent, names[0])
                        if (parentOrbit == null) {
                            parentOrbit = Orbit(names[0], mutableListOf(), null)
                            withoutParent.add(parentOrbit)
                        }
                        val child = findOrbit(withoutParent, names[1])
                        if (child != null) {
                            withoutParent.remove(child)
                            val children = child.objectsInOrbit
                            val realChild = Orbit(child.name, children, parentOrbit)
                            realChild.objectsInOrbit.forEach { it.parent = realChild }
                            // updateParentOrbit(withoutParent, realChild)
                            parentOrbit.objectsInOrbit.add(realChild)
                        } else {
                            parentOrbit.objectsInOrbit.add(Orbit(names[1], mutableListOf(), parentOrbit))
                        }
                    }
                }
        return withoutParent
    }

    fun updateParentOrbit(orbits: List<Orbit>, parent: Orbit) {
        if (orbits.any { it.parent?.name == parent.name }) {
            orbits.first { it.parent?.name == parent.name }.parent = parent
        } else {
            orbits.forEach {
                updateParentOrbit(it.objectsInOrbit, parent)
            }
        }
    }

    fun findOrbit(orbits: List<Orbit>, searched: String): Orbit? {
        if (orbits.any { it.name == searched }) {
            return orbits.first { it.name == searched }
        } else {
            orbits.forEach {
                val currentOrbit = findOrbit(it.objectsInOrbit, searched)
                if (currentOrbit != null) {
                    return currentOrbit
                }

            }
        }
        return null
    }

    fun flatOrbitList(orbit: Orbit): MutableSet<Orbit> {
        return if (orbit.objectsInOrbit.isEmpty()) {
            mutableSetOf(orbit)
        } else {
            val set = mutableSetOf(*orbit.objectsInOrbit.toTypedArray()).map {
                flatOrbitList(it)
            }.flatten().toMutableSet()
            set.add(orbit)
            set
        }
    }

    fun countAllEdges(orbits: List<Orbit>): Int {
        var count = 0
        for (orbit in orbits) {
            var mutableOrbit = orbit
            while (mutableOrbit.parent != null) {
                count++
                mutableOrbit = mutableOrbit.parent!!
            }
        }
        return count
    }

    fun getParents(orbit: Orbit): List<Orbit> {
        var parents = mutableListOf<Orbit>()
        var mutableOrbit = orbit
        while (mutableOrbit.parent != null) {
            parents.add(mutableOrbit.parent!!)
            mutableOrbit = mutableOrbit.parent!!
        }
        return parents
    }

    fun getPathBetweeenSantaAndYou(orbits: List<Orbit>) : Int {
        val you = findOrbit(orbits, "YOU")!!
        val santa = findOrbit(orbits, "SAN")!!

        val path = getParents(you).union(getParents(santa)).subtract(getParents(you).intersect(getParents(santa)))
        return path.size
    }

}