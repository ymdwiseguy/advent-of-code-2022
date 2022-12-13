package day06

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day06Test {

    private lateinit var day: Day06

    @BeforeEach
    fun setUp() {
        day = Day06()
    }

    @Test
    fun `part 1`() {
        day.part1(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")) shouldBe 7
        day.part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) shouldBe 5
        day.part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")) shouldBe 6
        day.part1(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) shouldBe 10
        day.part1(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) shouldBe 11
    }

    @Test
    fun `part 2`() {
        day.part2(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")) shouldBe 19
        day.part2(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) shouldBe 23
        day.part2(listOf("nppdvjthqldpwncqszvftbrmjlhg")) shouldBe 23
        day.part2(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) shouldBe 29
        day.part2(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) shouldBe 26
    }

}