package aoc2019

fun increasing(num:String): Boolean =
   (0 .. num.length - 2).fold(true){ac, ix ->
          ac && num[ix].toInt() <=  num[ix + 1].toInt()}

fun hasPair(num:String): Boolean =
    (0 .. num.length - 2).fold(false){ac, ix ->
        ac || num[ix] == num[ix + 1]}

fun hasPairStrict(num:String): Boolean {
            fun next(ix: Int) : Boolean {
                if (ix >= num.length  - 1) return true
                return num[ix] != num[ix + 1]
            }
            fun prev(ix: Int) : Boolean {
                if (ix == 0 ) return true
                return  (num[ix] != num[ix - 1])
            }
       return (0..num.length - 2).fold(false) { ac, ix ->
            ac || ( num[ix] == num[ix + 1]) && (next(ix + 1) && prev(ix))
        }
}

fun check(num:String, pairOk:(String) -> Boolean):Boolean{
    return increasing(num) && pairOk(num)
}
fun main() {

    println("Part 1")
    var count = (245182..790572).filter(){n -> check(n.toString(),::hasPair)}.count()
    println(count)

    println("Part 1")
    count = (245182..790572).filter(){n -> check(n.toString(),::hasPairStrict)}.count()
    println(count)
}