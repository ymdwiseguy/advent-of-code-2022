package day2

import readInput

fun main() {
    println("Part 1: ${Day02().part1(Day02().input)}")
    println("Part 2: ${Day02().part2(Day02().input)}")
}

class Day02 {

    val input = readInput("resources/day02/data")
    val testInput = readInput("resources/day02/data_test")

    fun part1(input: List<String>): Int {
        var score = 0
        input.map {
            val (player1, player2) = it.split(" ")
            score += findScoreForPlayer2(player1, player2)
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        input.map {
            val (player1, necessaryResult) = it.split(" ")
            score += findAnswerForPlayer2(player1, necessaryResult)
        }
        return score
    }

    // Rock A & X
    // Paper B & Y
    // Scissors C & Z
    fun findScoreForPlayer2(player1: String, player2: String): Int = when (player2) {
        "X" -> {
            1 + when (player1) {
                "A" -> 3
                "B" -> 0
                "C" -> 6
                else -> 0
            }
        }

        "Y" -> {
            2 + when (player1) {
                "A" -> 6
                "B" -> 3
                "C" -> 0
                else -> 0
            }
        }

        "Z" -> {
            3 + when (player1) {
                "A" -> 0
                "B" -> 6
                "C" -> 3
                else -> 0
            }
        }

        else -> {
            0
        }
    }

    // Rock A -> 1
    // Paper B -> 2
    // Scissors C -> 3
    // Loose X -> 0
    // Draw Y -> 3
    // Win Z -> 6
    fun findAnswerForPlayer2(player: String, necessaryResult: String): Int = when (necessaryResult) {
        "X" -> {
            0 + when (player) {
                "A" -> 3 // C
                "B" -> 1 // A
                "C" -> 2 // B
                else -> 0
            }
        }

        "Y" -> {
            3 + when (player) {
                "A" -> 1 // A
                "B" -> 2 // B
                "C" -> 3 // C
                else -> 0
            }
        }

        "Z" -> {
            6 + when (player) {
                "A" -> 2 // B
                "B" -> 3 // C
                "C" -> 1 // A
                else -> 0
            }
        }

        else -> {
            0
        }
    }


}

