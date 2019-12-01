package at.gerhofer.day1

object Day1 {

    fun calculateFuelRequirements(value: Int) : Int {
        return (kotlin.math.floor(value / 3.0) - 2).toInt()
    }

    fun calculateFuelRequirementsRecursively(value: Int) : Int {
        var fuel = calculateFuelRequirements(value)
        var summedFuel = fuel
        while (calculateFuelRequirements(fuel) > 0) {
            fuel = calculateFuelRequirements(fuel)
            summedFuel += fuel
        }
        return summedFuel
    }

}