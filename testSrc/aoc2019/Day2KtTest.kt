package aoc2019

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2KtTest {
    @Test
    fun runProgTest() {
        val p1 = mutableListOf<Int>(1,0,0,0,99)
        val p2 = mutableListOf<Int>(2,3,0,3,99)
        val p3 = mutableListOf<Int>(2,4,4,5,99,0)
        val p4 = mutableListOf<Int>(1,1,1,4,99,5,6,0,99)
        kotlin.test.assertTrue { runProg(p1) ==
                listOf(2,0,0,0,99)}

        kotlin.test.assertTrue { runProg(p2) ==
                listOf(2,3,0,6,99)}
        kotlin.test.assertTrue { runProg(p3) ==
                listOf(2,4,4,5,99,9801)}
        kotlin.test.assertTrue { runProg(p4) ==
                listOf(30,1,1,4,2,5,6,0,99)}


    }
}