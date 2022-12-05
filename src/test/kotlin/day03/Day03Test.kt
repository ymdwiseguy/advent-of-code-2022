package day03

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day03Test {

    private lateinit var day: Day03
    private val input: List<String> = Day03().testInput

    @BeforeEach
    fun setUp() {
        day = Day03()
    }

    @Test
    fun `part 1 should return the power consumption`() {
        Day03().part1(input) shouldBe 0
    }

    @Test
    fun `part 2 should return the life support rating`() {
        Day03().part2(input) shouldBe 0
    }

}