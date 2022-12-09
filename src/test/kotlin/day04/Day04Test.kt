package day04

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day04Test {

    private lateinit var day: Day04
    private val testInput: List<String> = Day04().testInput
    private val input: List<String> = Day04().input

    @BeforeEach
    fun setUp() {
        day = Day04()
    }

    @Test
    fun `part 1 should return 2`() {
        day.part1(testInput) shouldBe 2
    }

    @Test
    fun `part 1 with real data should return 602`() {
        day.part1(input) shouldBe 602
    }

    @Test
    fun `compartments fully overlap`() {
         day.compartmentsFullyOverlap("2-4,6-8") shouldBe false
         day.compartmentsFullyOverlap("2-3,4-5") shouldBe false
         day.compartmentsFullyOverlap("5-7,7-9") shouldBe false
         day.compartmentsFullyOverlap("2-8,3-7") shouldBe true
         day.compartmentsFullyOverlap("6-6,4-6") shouldBe true
         day.compartmentsFullyOverlap("2-6,4-8") shouldBe false
    }

    @Test
    fun `part 2 should return 4`() {
        day.part2(testInput) shouldBe 4
    }

    @Test
    fun `part 2 should return 891`() {
        day.part2(input) shouldBe 891
    }

}