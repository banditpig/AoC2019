package aoc2019
import util.*

fun pairs(range:Int):List<Pair<Int,Int>> {
    val list = mutableListOf<Pair<Int, Int>>()
      (0..range).forEach { x ->
          (0..range).map { Pair(x, it) }.toCollection(list)
      }
    return list
}
fun checkPair(pair: Pair<Int, Int>, prog:MutableList<Int>):Boolean {
    val (n, v) = pair
    prog[1] = n
    prog[2] = v
    return runProg(prog)[0] == 19690720
}

fun checkAllPairs(pairs : List<Pair<Int, Int>> , ints:List<Int> ):Int{

    tailrec fun checkEach(pairs:List<Pair<Int, Int>>, prog:List<Int>, ix:Int) : Pair<Int,Int>{
        if(checkPair(pairs[ix], prog.toMutableList())) return pairs[ix]
        return checkEach(pairs, prog, ix + 1)
    }

    val (n, v) = checkEach(pairs, ints, 0)
    return 100*n + v
}
fun runProg(prog:MutableList<Int>):List<Int>{
    tailrec fun evalProg(prog:MutableList<Int>, ix:Int):List<Int>{
        val op = prog[ix]
        if ( op == 99) return prog
        var result: Int = 0
        when {
            op == 1 -> result = prog[prog[ix + 1]] + prog[prog[ix + 2]]
            op == 2 -> result = prog[prog[ix + 1]] * prog[prog[ix + 2]]
        }

        prog[prog[ix + 3]] = result
        return evalProg(prog, ix + 4)
    }
    return evalProg(prog, 0)
}
fun main() {
    val line = readFileLines(fname = "resources/Day2.txt")[0]
    val ints= line.split(",").map { it.toInt() }

    val copy = ints.toMutableList()
    copy[1] = 12
    copy[2] = 2
    println("Part 1.")
    println(runProg(copy)[0])

    val pairs = pairs(99)
    println("Part 2.")
    println(checkAllPairs(pairs, ints))

}