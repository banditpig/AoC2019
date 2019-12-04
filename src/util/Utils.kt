package util

import java.io.File
interface Monoid<T> {
    fun combine(t1: T, t2: T): T
    fun empty(): T
}

fun <T> Monoid<T>.fold(ts: List<T>): T
        = ts.foldRight(this.empty()) { t: T, acc: T -> this.combine(t, acc) }

fun <U,V> Collection<U>.cartesian(other: Collection<V>): Sequence<Pair<U,V>> =
    this.asSequence().map { f -> other.asSequence().map { s-> f to s } }.flatten()

fun <U,V>  Sequence<U>.cartesian(other: Sequence<V>): Sequence<Pair<U,V>> =
    this.map { f -> other.map { s-> f to s } }.flatten()

fun readFileLinesAsInt(fname: String): List<Int> =
    readFileLinesAsT(fname) { s -> s.toInt()}

infix fun <P1, R, P2> ((P1) -> R).compose(f: (P2) -> P1): (P2) -> R {
    return { p1: P2 -> this(f(p1)) }
}
fun <T> readFileLinesAsT(fname: String, convert: (String ) -> T): List<T> =
    readFileLines(fname).map { convert(it) }

fun readFileLines(fname: String): List<String> {
    val input = mutableListOf<String>()
    File(fname).useLines { all -> input.addAll(all) }
    return input
}
