package aoc2019

import util.readFileLines
import kotlin.math.abs

typealias Point = Pair<Int,Int>

fun applyInstruction(instr:Pair<Char, Int>, point:Point ):List<Point> {
    val (op, d) = instr
    val (x, y) = point

     fun accumulate( ac:MutableList<Point>, p:Point):MutableList<Point>{
        ac.add(p)
        return ac
    }

    if(op == 'U') {
        return (1..d).fold(mutableListOf<Point>()){acc, n -> accumulate(acc,Point(x, y - n))}
    }
    if(op == 'D') {
        return (1..d).fold(mutableListOf<Point>()){acc, n -> accumulate(acc,Point(x, y + n))}
    }
    if(op == 'L'){
        return (1..d).fold(mutableListOf<Point>()){acc, n -> accumulate(acc,Point(x - n, y))}
    }
    return (1..d).fold(mutableListOf<Point>()){acc, n -> accumulate(acc,Point(x + n, y))}

}
fun parseInstruction(instr: String):Pair<Char,Int>{
    val op:Char = instr[0]
    val size = instr.substring(1).toInt()
    return Pair(op, size)
}
fun allInstructions(wire:List<String>):List<Pair<Char,Int>>{
    return wire.map { parseInstruction(it) }
}
fun runInstructions(instr:List<Pair<Char,Int>> ):List<Point>{

    var points = mutableListOf<Point>(Point(0,0))
    instr.map { points.addAll(applyInstruction(it, points.last())) }
    return points

}
fun intersections(p1:List<Point>, p2:List<Point>):List<Point> =
    p1.toSet().intersect(p2.toSet()).toList()


fun distance (p1:Point, p2:Point):Int{
    val (x1,y1) = p1
    val (x2,y2) = p2
    val d = abs(x1 - x2) + abs (y1 - y2)
    return d
}

fun main() {
    val wires = readFileLines(fname = "resources/Day3.txt")
    val wire1 = wires[0].split(",")
    val wire2 = wires[1].split(",")



    val p1 = runInstructions(allInstructions(wire1))
    val p2 = runInstructions(allInstructions(wire2))
    var inters = intersections(p1,p2)
    println("Part 1.");
    println(inters.map { distance(it, Point(0,0)) }.sorted()[1])
    println("Part 2.");
    println( inters.map { it -> p1.indexOf(it) + p2.indexOf(it) }.sorted()[1] )

}

