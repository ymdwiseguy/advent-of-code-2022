package day1

import kotlin.test.assertEquals

infix fun Any?.shouldBe(expected: Any?) {
    assertEquals(
        actual = this, expected = expected
    )
}