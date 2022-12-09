package day03

import readInput

fun main() {
    println("Part 1: ${Day03().part1(Day03().input)}")
    println("Part 2: ${Day03().part2(Day03().input)}")
}

class Day03 {
    val input = readInput("resources/day03/data")
    val testInput = readInput("resources/day03/data_test")

    fun part1(input: List<String>): Int {
        var count = 0
        input.map {
            count += convertPriorityCharToInt(getBackpackPriorityChar(it))
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val groups = input.windowed(size = 3, step = 3)
        var count = 0

        groups.forEach {
            count += convertPriorityCharToInt(
                findBadgeInBackPacks(it)
            )
        }

        return count
    }

    private fun findBadgeInBackPacks(backPacks: List<String>): Char {
        var badgeChar = 'a'
        val compareFirst = mutableListOf<Char>()


        backPacks[0].forEach { char ->
            if (char in backPacks[1]) {
                compareFirst.add(char)
            }
        }
        compareFirst.find {
            it in backPacks[2]
        }?.let { found ->
            badgeChar = found
        }
        return badgeChar
    }

    fun getBackpackPriorityChar(contents: String): Char {
        var priorityChar = 'a'
        val (compartment1, compartment2) = contents.chunked(contents.length / 2)
        compartment1.forEach { char ->
            compartment2.find { it == char }?.let { found ->
                priorityChar = found
            }
        }
        return priorityChar
    }

    fun convertPriorityCharToInt(char: Char) = char.code - if (char.isLowerCase()) 96 else 38
}