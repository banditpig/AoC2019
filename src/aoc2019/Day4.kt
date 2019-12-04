package aoc2019

fun increasing(num:String): Boolean =
   (0 .. num.length - 2).fold(true){ac, ix ->
          ac && num[ix].toInt() <=  num[ix + 1].toInt()}

fun hasPair(num:String): Boolean =
    (0 .. num.length - 2).fold(false){ac, ix ->
        ac || num[ix] == num[ix + 1]}

fun check(num:String ): Boolean = increasing(num) && hasPair(num)

fun main() {

    println("Part 1")
    var count = (245182..790572).filter(){n -> check(n.toString())}.count()
    println(count)
}

