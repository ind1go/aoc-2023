package dev.bencox.aoc2023

import java.lang.Integer.parseInt
import java.util.regex.Pattern

class `Day 1`: Exercise<Int>() {

    override fun part1(): Int {
        val total = getInputAsReader().lines().mapToInt { line ->
            val first = line.find { it.isDigit() } ?: throw Exception("Failed finding first number")
            val last = line.findLast { it.isDigit() } ?: throw Exception("Failed finding last number")
            val arr = parseInt(String(charArrayOf(first, last)))
            arr
        }.sum()
        return total
    }

    override fun part2(): Int {
        val numberOptions = "(\\d|zero|one|two|three|four|five|six|seven|eight|nine)"
        val p = Pattern.compile(".*?$numberOptions(.*$numberOptions)?.*?")

        val total = getInputAsReader().lines().mapToInt { line ->
            val matcher = p.matcher(line)
            assert(matcher.matches()) {
                "No match for line $line"
            }
            val first = matcher.group(1).let(::toDigit)
            val last = matcher.group(3)?.let(::toDigit) ?: first
            val arr = parseInt(String(charArrayOf(first, last)))
            arr
        }.sum()
        return total
    }

    private fun toDigit(input: String): Char {
        return when (input) {
            "one" -> '1'
            "two" -> '2'
            "three" -> '3'
            "four" -> '4'
            "five" -> '5'
            "six" -> '6'
            "seven" -> '7'
            "eight" -> '8'
            "nine" -> '9'
            else -> input[0]
        }
    }
}