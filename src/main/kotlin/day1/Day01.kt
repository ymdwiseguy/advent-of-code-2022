package day1

import readInput

class Day01 {

    val input = readInput("resources/day1/data")
    val testInput = readInput("resources/day1/data_test")

    fun part1(input: List<String>): Int {
        var count = 0
        input.forEachIndexed { index, s ->
            if (index > 0) {
                if (s.toInt() > input[index - 1].toInt()) count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

}
