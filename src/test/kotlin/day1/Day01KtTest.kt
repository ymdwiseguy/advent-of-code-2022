package day1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

class Day01Test{

    private lateinit var day: Day01
    private val input: List<String> = Day01().testInput

    @BeforeEach
    fun before(){
        day = Day01()
    }

    @Test
    fun `part 1 should return number of increases`() {
        day.part1(input) shouldBe 0
    }

    @Test
    fun `part 2 should return number of sliding window sum increases`() {
         day.part2(input) shouldBe 0
    }
}