package aoc2019

import util.readFileLines

data class Program(val prog: MutableList<Int>, val ix: Int, val input: Int, var output: Int)

sealed class Mode {
    object Immediate : Mode() {
        override fun toString(): String {
            return "Immediate"
        }
    }

    object Position : Mode() {
        override fun toString(): String {
            return "Position"
        }
    }
}

fun fromInt(i: Int): Mode {
    return when (i) {
        1 -> Mode.Immediate
        else -> Mode.Position
    }
}

sealed class Operation {
    data class Mul(val m1: Mode, val m2: Mode, val m3: Mode) : Operation()
    data class Add(val m1: Mode, val m2: Mode, val m3: Mode) : Operation()
    data class JpT(val m1: Mode, val m2: Mode) : Operation()
    data class JpF(val m1: Mode, val m2: Mode) : Operation()
    data class Ltn(val m1: Mode, val m2: Mode, val m3: Mode) : Operation()
    data class Eql(val m1: Mode, val m2: Mode, val m3: Mode) : Operation()
    data class Inp(val p: Mode) : Operation()
    data class Out(val p: Mode) : Operation()
    object End : Operation()
}

fun parseOp(op: Int): Operation {

    val ops = op.toString().padStart(5, '0')
    val theOp = ops.substring(ops.length - 2).toInt()
    val mode1 = fromInt(ops[ops.length - 3].toString().toInt())
    val mode2 = fromInt(ops[ops.length - 4].toString().toInt())
    val mode3 = fromInt(ops[ops.length - 5].toString().toInt())

    if (theOp == 1) return Operation.Add(mode1, mode2, mode3)
    if (theOp == 2) return Operation.Mul(mode1, mode2, mode3)
    if (theOp == 3) return Operation.Inp(mode1)
    if (theOp == 4) return Operation.Out(mode1)
    if (theOp == 5) return Operation.JpT(mode1, mode2)
    if (theOp == 6) return Operation.JpF(mode1, mode2)
    if (theOp == 7) return Operation.Ltn(mode1, mode2, mode3)
    if (theOp == 8) return Operation.Eql(mode1, mode2, mode3)

    if (theOp == 99) return Operation.End
    return Operation.End
}

fun evalOp(op: Operation, program: Program): Program {

    val (prg, ix, input, output) = program

    when (op) {
        is Operation.Mul -> {
            val (m1, m2, m3) = op
            val p1 = evalMode(m1, prg, ix + 1);
            val p2 = evalMode(m2, prg, ix + 2);
            prg[prg[ix + 3]] = p1 * p2
            return Program(prg, ix + 4, input, output)
        }
    }
    when (op) {
        is Operation.Add -> {
            val (m1, m2, m3) = op
            val p1 = evalMode(m1, prg, ix + 1);
            val p2 = evalMode(m2, prg, ix + 2);
            prg[prg[ix + 3]] = p1 + p2
            return Program(prg, ix + 4, input, output)

        }
    }
    when (op) {
        is Operation.Inp -> {
            val (m) = op
            val p = evalMode(m, prg, ix + 1);
            prg[prg[ix + 1]] = program.input
            return Program(prg, ix + 2, input, output)
        }
    }
    when (op) {
        is Operation.Out -> {
            val (m) = op
            val p = evalMode(m, prg, ix + 1);
            val ix1 = prg[ix + 1]
            val opt = if (m == Mode.Immediate) {
                prg[ix + 1]
            } else {
                prg[prg[ix + 1]]
            }
            return Program(prg, ix + 2, input, opt)
        }
    }
    when (op) {
        is Operation.JpT -> {
            val (m1, m2) = op
            val p1 = evalMode(m1, prg, ix + 1);
            val p2 = evalMode(m2, prg, ix + 2);
            return if (p1 != 0) Program(prg, p2, input, output)
            else Program(prg, ix + 3, input, output)
        }
    }
    when (op) {
        is Operation.JpF -> {
            val (m1, m2) = op
            val p1 = evalMode(m1, prg, ix + 1);
            val p2 = evalMode(m2, prg, ix + 2);
            return if (p1 == 0) Program(prg, p2, input, output)
            else Program(prg, ix + 3, input, output)
        }
    }
    when (op) {
        is Operation.Ltn -> {
            val (m1, m2) = op
            val p1 = evalMode(m1, prg, ix + 1);
            val p2 = evalMode(m2, prg, ix + 2);
            if (p1 < p2) {
                prg[prg[ix + 3]] = 1
            } else {
                prg[prg[ix + 3]] = 0
            }
            return Program(prg, ix + 4, input, output)
        }
    }
    when (op) {
        is Operation.Eql -> {
            val (m1, m2) = op
            val p1 = evalMode(m1, prg, ix + 1);
            val p2 = evalMode(m2, prg, ix + 2);
            if (p1 == p2) {
                prg[prg[ix + 3]] = 1
            } else {
                prg[prg[ix + 3]] = 0
            }
            return Program(prg, ix + 4, input, output)
        }
    }

    return program

}

fun evalMode(m: Mode, prog: MutableList<Int>, ix: Int): Int {

    return when (m) {
        Mode.Immediate -> prog[ix]
        Mode.Position -> prog[prog[ix]]
    }
}

fun runProgram(program: Program): Program {
    val (prog, ix) = program
    val operation = parseOp(prog[ix])
    if (operation == Operation.End) {
        return program
    }

    return runProgram(evalOp(operation, program))
}

fun main() {
    val line = readFileLines(fname = "resources/Day5.txt")[0]
    val ints = line.split(",").map { it.toInt() }

    println("Part 1")
    var program = Program(ints.toMutableList(), 0, 1, 0)
    program = runProgram(program)
    println(program.output)

    println("Part 2")
    program = Program(ints.toMutableList(), 0, 5, 0)
    program = runProgram(program)
    println(program.output)
}

