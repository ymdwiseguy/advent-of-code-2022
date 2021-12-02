package day2

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

class Day02Test{

    private lateinit var day: Day02
    private val input: List<String> = Day02().testInput

    @BeforeEach
    fun before(){
        day = Day02()
    }

    @Test
    fun `part 1 should return navigation result`() {
        day.part1(input) shouldBe 150
    }

    @Test
    fun `part 2 should return navigation result`() {
        day.part2(input) shouldBe 900
    }
}