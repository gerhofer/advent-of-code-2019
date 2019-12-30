package at.gerhofer

import java.io.File
import kotlin.math.ceil

object Day14 {

    lateinit var chemicalReactions: Map<Element, Reaction>
    var amountOfOre: Long = 0L
    val leftovers: MutableMap<Element, Int> = mutableMapOf()

    fun reset() {
        amountOfOre = 0L
        leftovers.clear()
    }

    fun calculateOreForFuel(filename: String): Long {
        File(filename)
                .useLines { lines ->
                    chemicalReactions = lines
                            .map { line ->
                                val fromAndTo = line.split("=>")
                                val sources = fromAndTo[0].split(",")
                                val result = fromAndTo[1]
                                val resultIngredient = Ingredient(result.trim())
                                resultIngredient.element to Reaction(resultIngredient.amount, sources.map { Ingredient(it.trim()) })
                            }
                            .plusElement(Element("ORE") to Reaction(1, emptyList()))
                            .toMap()

                    produceElement(Element("FUEL"), 1)
                    return amountOfOre

                }
    }

    fun produceAllTheFuel(filename: String): Int {
        var fuelCount = 0
        File(filename)
                .useLines { lines ->
                    chemicalReactions = lines
                            .map { line ->
                                val fromAndTo = line.split("=>")
                                val sources = fromAndTo[0].split(",")
                                val result = fromAndTo[1]
                                val resultIngredient = Ingredient(result.trim())
                                resultIngredient.element to Reaction(resultIngredient.amount, sources.map { Ingredient(it.trim()) })
                            }
                            .plusElement(Element("ORE") to Reaction(1, emptyList()))
                            .toMap()

                    while (amountOfOre < 1000000000000L) {
                        produceElement(Element("FUEL"), 1)
                        fuelCount++
                        if (fuelCount % 10000 == 0) {
                            println("$fuelCount fuel")
                        }
                    }
                    return fuelCount - 1
                }
    }

    private fun produceElement(element: Element, batches: Int) {
        if (element == Element("ORE")) {
            amountOfOre += batches
        }

        chemicalReactions[element]!!.ingredients.map {
            if (leftovers.getOrDefault(it.element, 0) < it.amount * batches) {
                val neededBatches = ceil((it.amount * batches - leftovers.getOrDefault(it.element, 0)).toDouble() / chemicalReactions[it.element]!!.amount).toInt()
                produceElement(it.element, neededBatches)
            }
            //println("used ${it.amount} of Element ${it.element} in order to produce ${element}")
            leftovers[it.element] = leftovers[it.element]!! - it.amount * batches
        }
        //println("produced ${chemicalReactions[element]!!.amount} of Element ${element}")
        leftovers[element] = leftovers.getOrDefault(element, 0) + chemicalReactions[element]!!.amount * batches
    }


    // first try
    private fun ingredientsInOre(element: Element): Int {
        val reaction = chemicalReactions[element]!!
        if (element == Element("ORE")) {
            return 1
        }
        return reaction.ingredients.map {
            val factor = ceil(it.amount.toDouble() / chemicalReactions[it.element]!!.amount.toDouble()).toInt()
            val ingredientsInOre = ingredientsInOre(it.element)
            println("Needing $factor * $ingredientsInOre of Element ${it.element} to produce $element")
            ingredientsInOre * factor
        }.sum()
    }

    data class Reaction(val amount: Int,
                        val ingredients: List<Ingredient>)

    data class Element(val name: String)

    data class Ingredient(val amount: Int,
                          val element: Element) {
        constructor(string: String) : this(string.split(" ")[0].trim().toInt(), Element(string.split(" ")[1].trim()))
    }

}