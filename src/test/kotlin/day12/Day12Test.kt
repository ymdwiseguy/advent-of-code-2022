package day12

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day12Test {

    private lateinit var day: Day12
    private val testInput: List<String> = Day12().testInput

    @BeforeEach
    fun setUp() {
        day = Day12()
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