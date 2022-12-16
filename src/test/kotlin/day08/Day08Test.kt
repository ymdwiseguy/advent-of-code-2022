package day08

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day08Test {

    private lateinit var day: Day08
    private val testInput: List<String> = Day08().testInput
    private val input: List<String> = Day08().input

    @BeforeEach
    fun setUp() {
        day = Day08()
    }

    @Test
    fun `part 1 should return 21`() {
        day.part1(testInput) shouldBe 21

    }

    @Test
    fun `part 1 with real data should return 1801`() {
        day.part1(input) shouldBe 1801

    }

    @Test
    fun `part 2 should return 8`() {
        day.part2(testInput) shouldBe 8
    }

    @Test
    fun `part 2 with real data should return 209880`() {
        day.part2(input) shouldBe 209880
    }

}