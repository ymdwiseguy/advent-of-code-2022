package day04

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day04Test {

    private lateinit var day: Day04
    private val testInput: List<String> = Day04().testInput

    @BeforeEach
    fun setUp() {
        day = Day04()
    }

    @Test
    fun `part 1 should return 0`() {
        day.part1(testInput) shouldBe 0

    }

    @Test
    fun `part 2 should return 0`() {
        day.part2(testInput) shouldBe 0
    }

}