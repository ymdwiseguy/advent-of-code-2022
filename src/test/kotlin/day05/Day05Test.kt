package day05

import day05.Day05.Instruction
import day05.Day05.Stack
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day05Test {

    private lateinit var day: Day05
    private val testInput: List<String> = Day05().testInput
    private val input: List<String> = Day05().input

    @BeforeEach
    fun setUp() {
        day = Day05()
    }

    @Test
    fun `part 1 should return CMZ`() {
        day.part1(testInput) shouldBe "CMZ"
    }

    @Test
    fun `part 1 with real data should return NTWZZWHFV`() {
         day.part1(input) shouldBe "NTWZZWHFV"
    }

    @Test
    fun `compute instructions`() {
        val (stacks: Map<Int, Stack>, instructions: List<Instruction>) = day.parseInput(
            testInput
        )

        stacks.followInstructions(instructions) shouldBe mapOf(
            1 to Stack(mutableListOf('C')),
            2 to Stack(mutableListOf('M')),
            3 to Stack(mutableListOf('P', 'D', 'N', 'Z')),
        )
    }

    @Test
    fun `parse instructions`() {
        day.parseInstructions(
            listOf(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
            )
        ) shouldBe listOf(
            Instruction(1, 2, 1),
            Instruction(3, 1, 3),
            Instruction(2, 2, 1),
            Instruction(1, 1, 2),
        )
    }

    @Test
    fun `number of stacks`() {
        stringsOfStacks.last().windowed(2, 4).size shouldBe 3
    }

    @Test
    fun `parse stacks`() {
        day.parseStacks(
            stringsOfStacks
        ) shouldBe mapOf(
            1 to Stack(mutableListOf('Z', 'N')),
            2 to Stack(mutableListOf('M', 'C', 'D')),
            3 to Stack(mutableListOf('P')),
        )
    }

    @Test
    fun `parse input`() {
        val result = day.parseInput(testInput)

        result.first shouldBe mapOf(
            1 to Stack(mutableListOf('Z', 'N')),
            2 to Stack(mutableListOf('M', 'C', 'D')),
            3 to Stack(mutableListOf('P')),
        )
        result.second shouldBe listOf(
            Instruction(1, 2, 1),
            Instruction(3, 1, 3),
            Instruction(2, 2, 1),
            Instruction(1, 1, 2),
        )
    }

    @Test
    fun `part 2 should return 0`() {
        day.part2(testInput) shouldBe 0
    }

    private companion object {
        val stringsOfStacks = listOf(
            "    [D]\n",
            "[N] [C]\n",
            "[Z] [M] [P]\n",
            " 1   2   3"
        )
    }
}