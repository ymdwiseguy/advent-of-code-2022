package day15

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day15Test {

    private lateinit var day: Day15
    private val testInput: List<String> = Day15().testInput

    @BeforeEach
    fun setUp() {
        day = Day15()
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