package day1

import readInput
import kotlin.Int as Calories

fun main() {
    println("Part 1: ${Day01().part1(Day01().input)}")
    println("Part 2: ${Day01().part2(Day01().input)}")
}

class Day01 {

    val input = readInput("resources/day01/data")
    val testInput = readInput("resources/day01/data_test")

    fun part1(input: List<String>): Calories = getAllElves(input).maxOf { it.calories() }


    fun part2(input: List<String>): Calories {
        val elves = getAllElves(input)

        val highestCalories = elves.maxOf { it.calories() }

        val restElves = elves.filter { it.calories() != highestCalories }

        val secondHighestCalories = restElves.maxOf { it.calories() }

        val restElves2 = restElves.filter { it.calories() != secondHighestCalories }

        return highestCalories + secondHighestCalories + restElves2.maxOf { it.calories() }
    }

    private fun getAllElves(input: List<String>): MutableList<Elf> {
        val elves = mutableListOf<Elf>()
        var currentElf = Elf(mutableListOf())

        input.forEach {
            if (it != "") {
                currentElf.food.add(it.toInt())
            } else {
                elves.add(currentElf)
                currentElf = Elf(mutableListOf())
            }
        }
        elves.add(currentElf)
        return elves
    }

    data class Elf(
        val food: MutableList<Calories>
    ) {
        fun calories(): Calories = food.sum()

    }

}
