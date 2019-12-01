package aoc2019

import java.io.File

fun readFileLinesAsInt(fname: String): List<Int> =
    readFileLines(fname).map { it.toInt() }

fun readFileLines(fname: String): List<String> {
    val input = mutableListOf<String>()
    File(fname).useLines { all -> input.addAll(all) }
    return input
}

fun basicFuelCalc(x: Int): Int = x.div(3) - 2

fun calcFuel(m: Int): Int {
    tailrec fun totalFuel(x: Int, acc: Int): Int {
        val fuel = basicFuelCalc(x)
        if (fuel <= 0) return acc
        return totalFuel(fuel, fuel + acc)
    }
    return totalFuel(m, 0)
}

fun main() {

    val numbers = readFileLinesAsInt("resources/Day1.txt")
    println("Part 1")
    println(numbers.map { basicFuelCalc(it) }.sum())

    println("Part 2")
    println(numbers.map { calcFuel(it) }.sum())
}
