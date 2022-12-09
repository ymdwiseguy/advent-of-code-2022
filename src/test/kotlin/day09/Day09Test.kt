package day09

import day09.Day09.Point
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day09Test {

    private lateinit var day: Day09
    private val testInput: List<String> = Day09().testInput
    private val testInput2: List<String> = Day09().testInputPt2

    @BeforeEach
    fun setUp() {
        day = Day09()
    }

    @Test
    fun `part 1 should return 13`() {
        day.part1(testInput) shouldBe 13

    }

    @Test
    fun `follow no movement`() {
        day.followPosition(
            Point(0, 0),
            Point(0, 0)
        ) shouldBe Point(0, 0)
    }

    @Test
    fun `follow right`() {
        day.followPosition(
            Point(-1, 0),
            Point(0, 0)
        ) shouldBe Point(-1, 0)

        day.followPosition(
            Point(-1, 0),
            Point(3, 0)
        ) shouldBe Point(2, 0)

        day.followPosition(
            Point(0, 0),
            Point(1, 0)
        ) shouldBe Point(0, 0)

        day.followPosition(
            Point(1, 0),
            Point(2, 0)
        ) shouldBe Point(1, 0)

        day.followPosition(
            Point(1, 0),
            Point(3, 0)
        ) shouldBe Point(2, 0)
    }

    @Test
    fun `follow left`() {
        day.followPosition(
            Point(1, 0),
            Point(0, 0)
        ) shouldBe Point(1, 0)

        day.followPosition(
            Point(0, 0),
            Point(-1, 0)
        ) shouldBe Point(0, 0)

        day.followPosition(
            Point(-1, 0),
            Point(-3, 0)
        ) shouldBe Point(-2, 0)

        day.followPosition(
            Point(5, 0),
            Point(-3, 0)
        ) shouldBe Point(-2, 0)
    }

    @Test
    fun `follow up`() {
        day.followPosition(
            Point(0, -1),
            Point(0, 0)
        ) shouldBe Point(0, -1)
        day.followPosition(
            Point(0, -1),
            Point(0, 3)
        ) shouldBe Point(0, 2)
        day.followPosition(
            Point(0, 0),
            Point(0, 1)
        ) shouldBe Point(0, 0)
        day.followPosition(
            Point(0, 1),
            Point(0, 2)
        ) shouldBe Point(0, 1)
        day.followPosition(
            Point(0, 1),
            Point(0, 3)
        ) shouldBe Point(0, 2)
    }

    @Test
    fun `follow down`() {
        day.followPosition(
            Point(0, 1),
            Point(0, 0)
        ) shouldBe Point(0, 1)

        day.followPosition(
            Point(0, 0),
            Point(0, -1)
        ) shouldBe Point(0, 0)

        day.followPosition(
            Point(0, -1),
            Point(0, -3)
        ) shouldBe Point(0, -2)

        day.followPosition(
            Point(0, 5),
            Point(0, -3)
        ) shouldBe Point(0, -2)
    }

    @Test
    fun `get head path`() {
        day.headPath(
            listOf(
                "R 4",
                "U 4",
                "L 3",
                "D 1",
            )
        ) shouldBe listOf(
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
            Point(3, 0),
            Point(4, 0),
            Point(4, 1),
            Point(4, 2),
            Point(4, 3),
            Point(4, 4),
            Point(3, 4),
            Point(2, 4),
            Point(1, 4),
            Point(1, 3),
        )
    }

    @Test
    fun `tail follows head`() {
        day.tailPath(
            listOf(
                Point(0, 0),
                Point(1, 0),
                Point(2, 0),
                Point(3, 0),
                Point(4, 0),
                Point(4, 1),
                Point(4, 2),
                Point(4, 3),
                Point(4, 4),
                Point(3, 4),
                Point(2, 4),
                Point(1, 4),
            )
        ) shouldBe listOf(
            Point(0, 0),
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
            Point(3, 0),
            Point(3, 0),
            Point(4, 1),
            Point(4, 2),
            Point(4, 3),
            Point(4, 3),
            Point(3, 4),
            Point(2, 4),
        )
    }

    @Test
    fun `change direction from right to up`() {
        day.followPosition(
            Point(3, 0),
            Point(4, 0)
        ) shouldBe Point(3, 0)

        day.followPosition(
            Point(3, 0),
            Point(4, 1)
        ) shouldBe Point(3, 0)

        day.followPosition(
            Point(3, 0),
            Point(4, 2)
        ) shouldBe Point(4, 1)
    }

    @Test
    fun `part 2 with old test data should return 1`() {
        day.part2(testInput) shouldBe 1
    }

    @Test
    fun `part 2 with new test data should return 36`() {
        day.part2(testInput2) shouldBe 36
    }

    @Test
    fun `p1 with real data should return 6256`() {
        day.part1(day.input) shouldBe 6256
    }

    @Test
    fun `p2 with real data should return 2665`() {
        day.part2(day.input) shouldBe 2665
    }

}