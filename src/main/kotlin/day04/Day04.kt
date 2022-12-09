package day04

import readInput

fun main() {
    println("Part 1: ${Day04().part1(Day04().input)}")
    println("Part 2: ${Day04().part2(Day04().input)}")
}

class Day04 {

    val input = readInput("resources/day04/data")
    val testInput = readInput("resources/day04/data_test")

    fun part1(input: List<String>): Int {
        var count = 0
        input.forEach {
            if(compartmentsFullyOverlap(it)){
                count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        input.forEach {
            if(compartmentsOverlap(it)){
                count++
            }
        }
        return count
    }

    private fun compartmentsOverlap(input: String): Boolean {
        val (range1, range2) = getRanges(input)
        return (range1.find { it in range2 } ?: 0) > 0 || (range2.find { it in range1 } ?: 0) > 0
    }

    fun compartmentsFullyOverlap(input: String): Boolean {
        val (range1, range2) = getRanges(input)
        return range1.all { it in range2 } || range2.all { it in range1 }
    }

    private fun getRanges(input: String): Pair<IntRange, IntRange> {
        val (c1, c2) = input.split(",")
        val (c1a, c1b) = c1.split("-")
        val (c2a, c2b) = c2.split("-")
        val range1 = c1a.toInt()..c1b.toInt()
        val range2 = c2a.toInt()..c2b.toInt()
        return Pair(range1, range2)
    }

}
