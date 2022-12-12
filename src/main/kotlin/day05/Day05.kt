package day05

import readInput

fun main() {
    println("Part 1: ${Day05().part1(Day05().input)}")
    println("Part 2: ${Day05().part2(Day05().input)}")
}

class Day05 {

    val input = readInput("resources/day05/data")
    val testInput = readInput("resources/day05/data_test")

    fun part1(input: List<String>): String {
        val (stacks: MutableMap<Int, Stack>, instructions: List<Instruction>) = parseInput(input)

        return stacks.followInstructions9000(instructions).map { mapEntry ->
            mapEntry.value.crates.last()
        }.joinToString(separator = "")
    }

    fun part2(input: List<String>): String {
        val (stacks: MutableMap<Int, Stack>, instructions: List<Instruction>) = parseInput(input)

        return stacks.followInstructions9001(instructions).map { mapEntry ->
            mapEntry.value.crates.last()
        }.joinToString(separator = "")
    }


    fun parseInput(input: List<String>): Pair<MutableMap<Int, Stack>, List<Instruction>> =
        input.indexOf("").let { emptyRowIndex ->
            Pair(
                parseStacks(input.subList(0, emptyRowIndex)),
                parseInstructions(input.subList(emptyRowIndex + 1, input.size))
            )
        }

    fun parseInstructions(strings: List<String>): List<Instruction> = strings.map {
        Instruction(
            howMany = it.split("move ").last().split(" from").first().toInt(),
            from = it.split("from ").last().split(" to").first().toInt(),
            to = it.split("to ").last().toInt()
        )
    }

    fun parseStacks(strings: List<String>): MutableMap<Int, Stack> {
        val stacks = mutableMapOf<Int, Stack>()
        for (i in 1..strings.last().windowed(2, 4).size) {
            stacks[i] = Stack(mutableListOf())
        }

        strings.reversed().forEachIndexed { rowIndex, row ->
            if (rowIndex > 0) {
                row.windowed(2, 4).forEachIndexed { stackId, chars ->
                    val c = chars.last()
                    if (!c.isWhitespace()) {
                        stacks[stackId + 1]?.crates?.add(c)
                    }
                }
            }
        }
        return stacks
    }

    data class Stack(
        val crates: MutableList<Char> = mutableListOf()
    )

    data class Instruction(
        val howMany: Int,
        val from: Int,
        val to: Int,
    )
}

fun MutableMap<Int, Day05.Stack>.followInstructions9000(instructions: List<Day05.Instruction>): MutableMap<Int, Day05.Stack> {
    instructions.forEach { instruction ->
        for (i in 1..instruction.howMany) {
            this[instruction.from]?.crates?.let { cratesFromStack ->
                this[instruction.to]?.crates?.add(
                    cratesFromStack.last()
                )
                cratesFromStack.removeLast()
            }
        }
    }
    return this
}

fun MutableMap<Int, Day05.Stack>.followInstructions9001(instructions: List<Day05.Instruction>): MutableMap<Int, Day05.Stack> {
    instructions.forEach { instruction ->
        val moving = mutableListOf<Char>()

        for (i in 1..instruction.howMany) {
            this[instruction.from]?.crates?.let { cratesFromStack ->
                moving.add(
                    cratesFromStack.last()
                )
                cratesFromStack.removeLast()
            }
        }

        this[instruction.to]?.crates?.addAll(
            moving.reversed()
        )
    }
    return this
}

