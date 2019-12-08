package aoc2019

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day5KtTest {

    @Test
    fun runProgramBasic() {
        val p1 = mutableListOf<Int>(1, 0, 0, 0, 99)
        val p2 = mutableListOf<Int>(2, 3, 0, 3, 99)
        val p3 = mutableListOf<Int>(2, 4, 4, 5, 99, 0)
        val p4 = mutableListOf<Int>(1, 1, 1, 4, 99, 5, 6, 0, 99)
        var p = Program(p1, 0, 0, 0)
        runProgram(p)

        kotlin.test.assertTrue {
            p.prog ==
                    listOf(2, 0, 0, 0, 99)
        }

        p = Program(p2, 0, 0, 0)
        runProgram(p)
        kotlin.test.assertTrue {
            p.prog ==
                    listOf(2, 3, 0, 6, 99)
        }

        p = Program(p3, 0, 0, 0)
        runProgram(p)
        kotlin.test.assertTrue {
            p.prog ==
                    listOf(2, 4, 4, 5, 99, 9801)
        }

        p = Program(p4, 0, 0, 0)
        runProgram(p)
        kotlin.test.assertTrue {
            p.prog ==
                    listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
        }

    }

    @Test
    fun largerTest() {
        var p = Program(
            mutableListOf(
                3,
                21,
                1008,
                21,
                8,
                20,
                1005,
                20,
                22,
                107,
                8,
                21,
                20,
                1006,
                20,
                31,
                1106,
                0,
                36,
                98,
                0,
                0,
                1002,
                21,
                125,
                20,
                4,
                20,
                1105,
                1,
                46,
                104,
                999,
                1105,
                1,
                46,
                1101,
                1000,
                1,
                20,
                4,
                20,
                1105,
                1,
                46,
                98,
                99
            ),
            0, 2, 0
        )
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(999, p.output)

        p = Program(
            mutableListOf(
                3,
                21,
                1008,
                21,
                8,
                20,
                1005,
                20,
                22,
                107,
                8,
                21,
                20,
                1006,
                20,
                31,
                1106,
                0,
                36,
                98,
                0,
                0,
                1002,
                21,
                125,
                20,
                4,
                20,
                1105,
                1,
                46,
                104,
                999,
                1105,
                1,
                46,
                1101,
                1000,
                1,
                20,
                4,
                20,
                1105,
                1,
                46,
                98,
                99
            ),
            0, 8, 0
        )
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1000, p.output)

        p = Program(
            mutableListOf(
                3,
                21,
                1008,
                21,
                8,
                20,
                1005,
                20,
                22,
                107,
                8,
                21,
                20,
                1006,
                20,
                31,
                1106,
                0,
                36,
                98,
                0,
                0,
                1002,
                21,
                125,
                20,
                4,
                20,
                1105,
                1,
                46,
                104,
                999,
                1105,
                1,
                46,
                1101,
                1000,
                1,
                20,
                4,
                20,
                1105,
                1,
                46,
                98,
                99
            ),
            0, 9, 0
        )
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1001, p.output)

    }

    @Test
    fun runProgramJmps() {
        var p = Program(mutableListOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 0, 8, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)

        p = Program(mutableListOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 0, 0, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)
//
        p = Program(mutableListOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 0, 0, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)

        p = Program(mutableListOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 0, 4, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)
    }


    @Test
    fun runProgramEqsLt() {
        var p = Program(mutableListOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 0, 8, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)

        p = Program(mutableListOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 0, 12, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)

        //
        p = Program(mutableListOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 0, 12, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)

        p = Program(mutableListOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 0, 4, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)
//
        p = Program(mutableListOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 0, 4, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)

        p = Program(mutableListOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 0, 8, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)
//
        p = Program(mutableListOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 0, 4, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)

        p = Program(mutableListOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 0, 12, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)

    }

    @Test
    fun runProgramEqsLtPos() {
        var p = Program(mutableListOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 0, 8, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(1, p.output)

        p = Program(mutableListOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 0, 12, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)
    }

    @Test
    fun runProgramMode() {
        var p = Program(mutableListOf(1002, 4, 3, 4, 33), 0, 0, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(99, p.prog.last())
    }

    @Test
    fun runProgramIO() {
        //3,0,4,0,99 outputs whatever it gets as input,
        // then halts.

        var p = Program(mutableListOf(3, 0, 4, 0, 99), 0, 123, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(123, p.output)

        p = Program(mutableListOf(3, 0, 4, 0, 99), 0, -1, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(-1, p.output)

        p = Program(mutableListOf(3, 0, 4, 0, 99), 0, 0, 0)
        p = aoc2019.runProgram(p)
        kotlin.test.assertEquals(0, p.output)

    }
}