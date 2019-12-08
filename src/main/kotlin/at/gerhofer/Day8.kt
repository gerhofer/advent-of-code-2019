package at.gerhofer

data class Count(val ones: Int,
                 val twos: Int,
                 val zeroes: Int)

object Day8 {

    fun getLayers(width: Int, height: Int, input: String): Int {
        val layers = input.chunked(width * height)


        val layerCounts = layers.map { layer ->
            val zeroes = layer.toList().map { it.toString().toInt() }
                    .count { it == 0 }
            val ones = layer.toList().map { it.toString().toInt() }
                    .count { it == 1 }
            val twos = layer.toList().map { it.toString().toInt() }
                    .count { it == 2 }
            Count(ones, twos, zeroes)
        }

        val layerWithLeastZeroes = layerCounts.minBy { it.zeroes }

        return layerWithLeastZeroes!!.ones * layerWithLeastZeroes.twos
    }

    fun getImage(width: Int, height: Int, input: String): String {
        val layers = input.chunked(width * height)
        var finalLayer = layers.first().toMutableList()
        layers.drop(1)

        layers.forEach { newLayer ->
            for (i in 0 until finalLayer.count()) {
                if (finalLayer[i] == '2') {
                    finalLayer[i] = newLayer[i]
                }
            }
        }

        var lines = finalLayer.chunked(width)
        lines.forEach { it.forEach { print(if (it == '0') ' ' else '#') }; println() }

        return finalLayer.toString()
    }
}