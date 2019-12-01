package util

import java.io.File

fun readFileLinesAsInt(fname: String): List<Int> =
    readFileLinesAsT(fname) { s -> s.toInt()}

fun <T> readFileLinesAsT(fname: String, convert: (String ) -> T): List<T> =
    readFileLines(fname).map { convert(it) }

fun readFileLines(fname: String): List<String> {
    val input = mutableListOf<String>()
    File(fname).useLines { all -> input.addAll(all) }
    return input
}
