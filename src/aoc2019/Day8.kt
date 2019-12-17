package aoc2019

import util.readFileLines

fun flatLayers(s: String, w: Int, h: Int): List<List<Int>> {
    return s.chunked(1).map { it -> it.toInt() }.chunked(w * h)
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

fun threadAndColour(ix: Int, layers: List<List<Int>>): Int {
    return colour(layers.map { it[ix] })
}

tailrec fun colour(pixels: List<Int>): Int {
    return when (pixels[0]) {
        0 -> 0
        1 -> 1
        else -> colour(pixels.drop(1))
    }
}

fun mergeLayers(layers: List<List<Int>>, w: Int, h:Int): List<Int> {
    return (0 until w * h).map { ix -> threadAndColour(ix, layers) }
}

fun convertX(x: Int): String {
    if (x == 0) return " "
    return "*"
}

fun main() {
    var digits = readFileLines("resources/Day8.txt")[0]

    val ll = flatLayers(digits, 25, 6)
        .map { l -> countDigits(l, 0, 1, 2) }
        .sortedWith(compareBy { it.first })
    println("Part 1.")
    println(ll[0].third * ll[0].second)

    var part2 = flatLayers(digits, 25, 6)
    val messageLayer = mergeLayers(part2, 25, 6).chunked(25)

    println("Part 2.") //and squint a bit!
    messageLayer.forEach { it.forEach { x -> print(convertX(x)) }; println("") }

}