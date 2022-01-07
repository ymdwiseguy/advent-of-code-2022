package day04

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day04Test {

    private lateinit var day: Day04
    private val input: List<String> = Day04().input
    private val testInput: List<String> = Day04().testInput

    @BeforeEach
    fun setUp() {
        day = Day04()
    }

    @Test
    fun `part 1 should return the score of the winning card`() {
        day.part1(testInput) shouldBe 4512
        day.part1(input) shouldBe 39902

    }

    @Test
    fun `part 2 should return the score of the loosing card`() {
        day.part2(testInput) shouldBe 1924
        day.part2(input) shouldBe 26936
    }

}