package day04

import readInput

fun String.toIntList(delimiter: String = " "): List<Int> = this.split(delimiter).mapNotNull {
    if (it.isNotEmpty()) {
        it.toInt()
    } else null
}

const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"

fun main() {
    Day04().part2(Day04().testInput)
}

class Day04 {

    val input = readInput("resources/day04/data")
    val testInput = readInput("resources/day04/data_test")


    fun part1(input: List<String>): Int {

        val winners = mutableListOf<BingoField>()
        val numbers = input.first().toIntList(",")

        with(Bingo(bingoFieldsFromInput(input))) {

            numbers.map { number ->
                if (winners.size > 0) return@map

                checkWinner().let { winnerBoards ->
                    if (winnerBoards.size > 0) {
                        winners.addAll(winnerBoards)
                    } else draw(number)
                }
            }
        }

        printBoards(winners)

        return winners.maxOf { it.score() }
    }

    fun part2(input: List<String>): Int {

        val losers = mutableListOf<BingoField>()
        val numbers = input.first().toIntList(",")

        val bingo = Bingo(bingoFieldsFromInput(input))
        losers.addAll(bingo.fields)

        numbers.map { number ->
            if (losers.size == 1 && losers.any { it.hasWon() }) {
                return@map
            } else {
                bingo.draw(number)
                if (losers.size > 1) losers.removeAll { it.hasWon() }
            }
        }

        printBoards(losers)

        return losers.maxOf { it.score() }
    }

    private fun printBoards(bingoFields: List<BingoField>) {
        bingoFields.map { println(it) }
    }

    private fun bingoFieldsFromInput(
        input: List<String>
    ): List<BingoField> {
        var fieldNumber = 0
        val fields = mutableListOf<BingoField>()
        input.forEachIndexed { index, row ->

            if (index > 1) {
                when (index % 6) {
                    1 -> Unit
                    0 -> {
                        fields[fieldNumber].numberFields.addAll(row.toIntList())
                        fieldNumber++
                    }
                    else -> {
                        if (fields.lastIndex < fieldNumber) {
                            fields.add(fieldNumber, BingoField())
                        }
                        fields[fieldNumber].numberFields.addAll(row.toIntList())
                    }
                }
            }
        }
        return fields
    }


}

data class Bingo(
    val fields: List<BingoField>,
    val numbers: MutableList<Int> = mutableListOf(),
) {

    fun draw(number: Int) {
        this.numbers.add(number)
        this.fields.forEach {
            it.addNumber(number)
        }
    }

    fun checkWinner(): MutableList<BingoField> {
        val winners = mutableListOf<BingoField>()
        fields.map {
            if (it.hasWon()) {
                winners.add(it)
            }
        }
        return winners
    }
}

data class BingoField(
    val numberFields: MutableList<Int> = mutableListOf(),
    val numbers: MutableList<Int> = mutableListOf(),
) {

    fun addNumber(number: Int) {
        numbers.add(number)
    }

    fun hasWon(): Boolean = virtualRows().any {
        numbers.containsAll(it)
    }

    fun score(): Int = numberFields.filter { (it in numbers).not() }.sum() * numbers.last()

    override fun toString(): String {
        var fieldString = "\n"
        numberFields.forEachIndexed { index, number ->
            if (number < 10) fieldString += " "
            fieldString += if (numbers.contains(number)) "$ANSI_RED$number$ANSI_RESET " else "$number "
            if (index % 5 == 4) {
                fieldString += "\n"
            }
        }
        fieldString += "\nLast drawn number ${numbers.last()}\nScore: ${score()}\n"

        return fieldString
    }

    private fun virtualRows(): List<List<Int>> {
        val rows = mutableListOf<List<Int>>()

        rows.add(numberFields.subList(0, 5))
        rows.add(numberFields.subList(5, 10))
        rows.add(numberFields.subList(10, 15))
        rows.add(numberFields.subList(15, 20))
        rows.add(numberFields.subList(20, 25))
        rows.add(numberFields.filterIndexed { index, i -> index % 5 == 0 })
        rows.add(numberFields.filterIndexed { index, i -> index % 5 == 1 })
        rows.add(numberFields.filterIndexed { index, i -> index % 5 == 2 })
        rows.add(numberFields.filterIndexed { index, i -> index % 5 == 3 })
        rows.add(numberFields.filterIndexed { index, i -> index % 5 == 4 })

        return rows
    }
}

