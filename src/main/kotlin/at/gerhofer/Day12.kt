package at.gerhofer

import java.io.File
import kotlin.math.ceil

object Day14 {

    lateinit var chemicalReactions: Map<Ingredient, List<Ingredient>>

    fun calculateOreForFuel(filename: String): Int {
        File(filename)
                .useLines { lines ->
                    chemicalReactions = lines
                            .map { line ->
                                val fromAndTo = line.split("=>")
                                val sources = fromAndTo[0].split(",")
                                val result = fromAndTo[1]
                                Ingredient(result.trim()) to sources.map { Ingredient(it.trim()) }
                            }
                            .plusElement(Ingredient(1, "ORE") to emptyList())
                            .toMap()

                }
    }



    // first try
    private fun ingredientsInOre(ingredient: Ingredient): Int {
        return if (ingredient.element == "ORE") {
            ingredient.amount
        } else {
            chemicalReactions[ingredient]!!.map {
                val factor = ceil(it.amount.toDouble() / chemicalReactions.keys.find { key -> key.element == it.element }!!.amount).toInt()
                ingredientsInOre(it) * factor
            }.sum()
        }
    }

    data class Ingredient(val amount: Int,
                          val element: String) {
        constructor(string: String) : this(string.split(" ")[0].trim().toInt(), string.split(" ")[1].trim())

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Ingredient

            if (element != other.element) return false

            return true
        }

        override fun hashCode(): Int {
            return element.hashCode()
        }


    }

}