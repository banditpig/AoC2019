package aoc2019

import util.*

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
