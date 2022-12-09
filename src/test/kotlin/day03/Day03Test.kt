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
    fun `part 1 should return 157`() {
        Day03().part1(input) shouldBe 157
    }

    @Test
    fun `get backpacks priority`() {
        day.getBackpackPriorityChar("vJrwpWtwJgWrhcsFMMfFFhFp") shouldBe 'p'
        day.getBackpackPriorityChar("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL") shouldBe 'L'
        day.getBackpackPriorityChar("PmmdzqPrVvPwwTWBwg") shouldBe 'P'
        day.getBackpackPriorityChar("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn") shouldBe 'v'
        day.getBackpackPriorityChar("ttgJtRGJQctTZtZT") shouldBe 't'
        day.getBackpackPriorityChar("CrZsJsPPZsGzwwsLwLmpwMDw") shouldBe 's'
    }

    @Test
    fun `map char to priority`() {
        day.convertPriorityCharToInt('a') shouldBe 1
        day.convertPriorityCharToInt('z') shouldBe 26
        day.convertPriorityCharToInt('A') shouldBe 27
        day.convertPriorityCharToInt('Z') shouldBe 52
    }

    @Test
    fun `part 2 should return 70`() {
        Day03().part2(input) shouldBe 70
    }

}