package day06

import readInput

fun main() {
    println("Part 1: ${Day06().part1(Day06().input)}")
    println("Part 2: ${Day06().part2(Day06().input)}")
}

class Day06 {

    val input = readInput("resources/day06/data")

    fun part1(input: List<String>): Int = findDistinctChars(input.first(), 4)

    fun part2(input: List<String>): Int = findDistinctChars(input.first(), 14)

    private fun findDistinctChars(input: String, numberOfChars: Int): Int {
        var position = 0
        input.windowed(
            numberOfChars, 1
        ).mapIndexed { index: Int, chars: String ->
            if (chars.toSet().size == numberOfChars && position == 0) {
                position = index + numberOfChars
            }
        }

        return position
    }
}
