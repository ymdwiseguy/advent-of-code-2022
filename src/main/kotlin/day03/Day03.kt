package day03

import readInput


private val moreThan: (first: Int, second: Int) -> Boolean = { x, y -> x > y }
private val lessOrEqual: (first: Int, second: Int) -> Boolean = { x, y -> x <= y }

private fun checkCommonBit(bitList: List<String>, criteria: (first: Int, second: Int) -> Boolean): String {
    var zeroCount = 0
    var oneCount = 0
    bitList.map { if (it == "0") zeroCount++ else oneCount++ }
    return if (criteria(zeroCount, oneCount)) "0" else "1"

}

class Day03 {
    val input = readInput("resources/day03/data")
    val testInput = readInput("resources/day03/data_test")

    fun part1(input: List<String>): Int = Power(input).powerConsumption()
    fun part2(input: List<String>): Int = LifeSupport(input).lifeSupportRating()
}

data class Power(
    val gammaRate: Int,
    val epsilonRate: Int,
) {
    constructor(input: List<String>) : this(
        getPowerRate(input) { bitsAtPosition -> checkCommonBit(bitsAtPosition, moreThan) },
        getPowerRate(input) { bitsAtPosition -> checkCommonBit(bitsAtPosition, lessOrEqual) }
    )

    fun powerConsumption(): Int = gammaRate * epsilonRate

    companion object {
        private fun getPowerRate(
            input: List<String>,
            criteria: (List<String>) -> String
        ): Int {
            var bitString = ""
            for (i in 0 until input.first().length) {
                val bitsAtPosition = getBitsAtPosition(input, i)
                bitString += criteria(bitsAtPosition)
            }
            return bitString.toInt(2)
        }

        private fun getBitsAtPosition(input: List<String>, position: Int): List<String> =
            input.map { it.substring(position, position + 1) }
    }
}

data class LifeSupport(
    val oxygenGeneratorRating: Int,
    val cO2ScrubberRating: Int,
) {

    constructor(input: List<String>) : this(
        getLifeSupportValue(input, moreThan),
        getLifeSupportValue(input, lessOrEqual)
    )

    fun lifeSupportRating(): Int = oxygenGeneratorRating * cO2ScrubberRating

    companion object {
        private fun getLifeSupportValue(input: List<String>, criteria: (first: Int, second: Int) -> Boolean): Int {
            var filterList = input
            var i = 0
            while (filterList.size > 1) {
                filterList = filterStep(filterList, i, criteria)
                i++
            }
            return filterList.first().toInt(2)
        }

        private fun filterStep(
            input: List<String>,
            position: Int,
            criteria: (first: Int, second: Int) -> Boolean
        ): List<String> =
            input.filter {
                it.substring(position, position + 1) == checkCommonBit(
                    getBitsAtPosition(input, position), criteria
                )
            }

        private fun getBitsAtPosition(input: List<String>, position: Int): List<String> =
            input.map { it.substring(position, position + 1) }

    }
}