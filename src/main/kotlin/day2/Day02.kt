package day2

import readInput

class Day02 {

    val input = readInput("resources/day02/data")
    val testInput = readInput("resources/day02/data_test")

    fun part1(input: List<String>): Int {
        val position = Postion(0, 0, 0)
        input.map {
            position.navigate(NavigationCommand(it))
        }

        return position.finalPosition()
    }

    fun part2(input: List<String>): Int {
        val position = Postion(0, 0, 0)
        input.map { position.navigateForAim((NavigationCommand(it))) }
        return position.finalPosition()
    }

}

