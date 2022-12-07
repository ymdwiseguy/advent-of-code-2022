package day2

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

class Day02Test {

    private lateinit var day: Day02
    private val input: List<String> = Day02().testInput

    @BeforeEach
    fun before() {
        day = Day02()
    }

    @Test
    fun `find score`() {
        day.findScoreForPlayer2("A", "Y") shouldBe 8
        day.findScoreForPlayer2("B", "X") shouldBe 1
        day.findScoreForPlayer2("C", "Z") shouldBe 6
    }

    @Test
    fun `find needed result`() {
        day.findAnswerForPlayer2("A", "Y") shouldBe 4
        day.findAnswerForPlayer2("B", "X") shouldBe 1
        day.findAnswerForPlayer2("C", "Z") shouldBe 7
    }

    @Test
    fun `part 1 should score 15 points`() {
        day.part1(input) shouldBe 15
    }

    @Test
    fun `part 2 should return 12`() {
        day.part2(input) shouldBe 12
    }
}