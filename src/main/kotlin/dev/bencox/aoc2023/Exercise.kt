package dev.bencox.aoc2023

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.measureNanoTime

abstract class Exercise<T> {

    abstract fun part1(): T

    abstract fun part2(): T

    data class TimedRun<T>(val result: T, val time: Long) {
        override fun toString(): String {
            return "$result (in ${String.format("%.6f", time / 1_000_000_000.0)}s)"
        }
    }

    private fun <T> time(function: () -> T): TimedRun<T> {
        var result: T
        val time = measureNanoTime {
            result = function()
        }
        return TimedRun(result, time)
    }

    fun run() = ExerciseRun(javaClass.simpleName, time(::part1), time(::part2))

    data class ExerciseRun<T>(val name: String, val part1: TimedRun<T>, val part2: TimedRun<T>) {

        override fun toString(): String {
            return buildString {
                appendLine("=== $name ===").appendLine()
                appendLine("Part 1: $part1")
                appendLine("Part 2: $part2").appendLine()
            }
        }

    }

    fun getInputAsReader(): BufferedReader {
        val inS = {}::class.java.getResourceAsStream("/${javaClass.simpleName}.txt") ?: throw Exception("Failed finding file")
        return BufferedReader(InputStreamReader(inS))
    }

}