package day1

import readInput

class Day01 {

    val input = readInput("resources/day01/data")
    val testInput = readInput("resources/day01/data_test")

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
        val newList = mutableListOf<String>()
        input.forEachIndexed { i, value ->
            if(i > 0 && i < input.size -1){
                val sum = input[i-1].toInt() + value.toInt() + input[i+1].toInt()
                newList.add(sum.toString())
            }
        }
        return part1(newList)
    }

}
