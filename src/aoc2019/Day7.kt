package aoc2019

import util.permutations
import util.readFileLines

fun evalPart2(seqnc:List<String>, prgCode:List<Int>):Int{

    val (a,b,c,d,e) = seqnc.map {it.toInt()}
    var ampA = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(a,0),0 ,name = "A")
    var ampB = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(b),0, name="B" )
    var ampC = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(c),0, name="C")
    var ampD = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(d),0 , name="D")
    var ampE = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(e),0 , name="E")
    var  done = false
    while (! done){
        ampA.state = Program.State.RUNNING
        ampA = runProgram(ampA)
        ampB.input.add(ampA.output)

        ampB.state = Program.State.RUNNING
        ampB = runProgram(ampB)
        ampC.input.add(ampB.output)

        ampC.state = Program.State.RUNNING
        ampC = runProgram(ampC)
        ampD.input.add(ampC.output)

        ampD.state = Program.State.RUNNING
        ampD = runProgram(ampD)
        ampE.input.add(ampD.output)

        ampE.state = Program.State.RUNNING
        ampE = runProgram(ampE)
        ampA.input.add(ampE.output)

        done =  ampA.state == Program.State.COMPLETED
                &&
                ampB.state == Program.State.COMPLETED
                &&
                ampC.state == Program.State.COMPLETED
                &&
                ampD.state == Program.State.COMPLETED
                &&
                ampE.state == Program.State.COMPLETED

    }

    return ampE.output
}


fun evalPart1(seqnc:List<String>, prgCode:List<Int>):Int{

    val (a,b,c,d,e) = seqnc.map {it.toInt()}
    var ampA = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(a,0),0 )
    ampA = runProgram(ampA)

    var ampB = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(b,ampA.output),0 )
    ampB = runProgram(ampB)

    var ampC = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(c,ampB.output),0 )
    ampC = runProgram(ampC)

    var ampD = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(d,ampC.output),0 )
    ampD = runProgram(ampD)

    var ampE = Program(prgCode.toMutableList(), 0, mutableListOf<Int>(e,ampD.output),0 )
    ampE = runProgram(ampE)

    return ampE.output
}

fun evalSequence(seq:List<List<String>>, prgCode:List<Int>, eval:(List<String>, List<Int>) ->Int): List<Int> =
    seq.map { s -> eval(s, prgCode) }

fun main() {

    val line = readFileLines(fname = "resources/Day7.txt")[0]
    val ints = line.split(",").map { it.toInt() }
    val phases = mutableListOf<String>("0","1","2","3","4").permutations().toList()

    val all = evalSequence(phases, ints,::evalPart1)
    println("Part 1.")
    println(all.max() )
    println()

    val phases2 = mutableListOf<String>("5","6","7","8","9").permutations().toList()
    val all2 = evalSequence(phases2, ints, ::evalPart2)
    println("Part 2.")
    println(all2.max() )


}