package aoc2019

import util.readFileLines

fun flatLayers(s: String): List<List<Int>> {
    return s.chunked(1).map { it -> it.toInt() }.chunked(25 * 6)
}

fun countDigits(layer: List<Int>, d1: Int, d2: Int, d3: Int): Triple<Int, Int, Int> {

    return layer.fold(Triple(0, 0, 0))
    { (a, b, c), i ->
        when (i) {
            d1 -> Triple(a + 1, b, c)
            d2 -> Triple(a, b + 1, c)
            d3 -> Triple(a, b, c + 1)
            else -> Triple(a, b, c)
        }
    }
}


fun main() {
    var digits = readFileLines("resources/Day8.txt")[0]

    val ll = flatLayers(digits)
                                   .map { l -> countDigits(l, 0, 1 ,2) }
                                   .sortedWith(compareBy {it.first})
    println("Part 1.")
    println(ll[0].third  * ll[0].second)
}