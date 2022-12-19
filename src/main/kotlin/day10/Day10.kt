package day10

import day10.Day10.Command.Add
import day10.Day10.Command.Noop
import readInput

fun main() {
    println("Part 1: ${Day10().part1(Day10().input)}")
    // TODO: display is off by one
    println("Part 2: ${Day10().part2(Day10().input)}")
}

class Day10 {

    val input = readInput("resources/day10/data")
    val testInput = readInput("resources/day10/data_test")

    fun part1(input: List<String>): Int {
        val commands: List<Command> = parseCommands(input)
        val register = Register()

        commands.forEach {
            register.handleCommand(it)
        }

        return register.finalSignalStrength()
    }

    private fun parseCommands(input: List<String>): List<Command> = input.map {
        when {
            it.startsWith("addx ") -> Add(it.split("addx ").last().toInt())
            it.startsWith("noop") -> Noop
            else -> TODO("this command is not yet implemented")
        }
    }

    fun part2(input: List<String>): String {
        val commands: List<Command> = parseCommands(input)
        val register = Register()

        commands.forEach {
            register.handleCommand(it)
        }

        val display = Display()
        display.setRegisterValues(register)
        return display.toString()

    }

    enum class Pixel {
        LIT,
        DARK;

        override fun toString(): String {
            return when (this) {
                LIT -> "#"
                DARK -> "."
            }
        }
    }

    data class Display(
        val pixels: MutableList<Pixel> = mutableListOf()
    ) {
        fun setRegisterValues(register: Register) {
            register.cycles.forEachIndexed { index, registerValue ->
                val cycle = (index%40)+1
                val spritePosition = registerValue-1 .. registerValue+1
                val pixel = if (cycle in spritePosition) Pixel.LIT else Pixel.DARK
                pixels.add(pixel)
                println("Position: $spritePosition - cycle: ${index+1} - register value: $registerValue - pixel: $pixel")
            }
        }

        override fun toString(): String {
            return "\n" +pixels.joinToString(separator = "").windowed(40, step = 40).joinToString(separator = "\n")
        }
    }

    sealed class Command() {
        data class Add(
            val count: Int,
        ) : Command()

        object Noop : Command()
    }

    data class Register(
        val cycles: MutableList<Int> = mutableListOf(),
        var currentValue: Int = 1
    ) {
        fun handleCommand(command: Command) {
            when (command) {
                is Add -> {
                    cycles.add(currentValue)
                    currentValue += command.count
                    cycles.add(currentValue)
                }

                is Noop -> {
                    cycles.add(currentValue)
                }
            }
        }

        private fun signalStrengthAtCycle(position: Int): Int {
            val registerValue = cycles[position - 2]
            return registerValue * position
        }

        fun finalSignalStrength() = signalStrengthAtCycle(20) +
                signalStrengthAtCycle(60) +
                signalStrengthAtCycle(100) +
                signalStrengthAtCycle(140) +
                signalStrengthAtCycle(180) +
                signalStrengthAtCycle(220)
    }
}
