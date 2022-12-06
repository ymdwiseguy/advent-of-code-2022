package day1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

class Day01Test {

    private lateinit var day: Day01
    private val input: List<String> = Day01().testInput

    @BeforeEach
    fun before() {
        day = Day01()
    }

    @Test
    fun `calculate an elves calories`() {
        val elf = Day01.Elf(
            mutableListOf(1000, 2000, 3000)
        )
        elf.calories() shouldBe 6000
    }

    @Test
    fun `part 1 should return highest calories value`() {
        day.part1(input) shouldBe 24000
    }

    @Test
    fun `part 2 should return top three calories summarized`() {
        day.part2(input) shouldBe 45000
    }
}