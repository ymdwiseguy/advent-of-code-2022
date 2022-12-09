package day09

import readInput
import kotlin.math.absoluteValue

fun main() {
    println("Part 1: ${Day09().part1(Day09().input)}")
    println("Part 2: ${Day09().part2(Day09().input)}")
}

class Day09 {

    val input = readInput("resources/day09/data")
    val testInput = readInput("resources/day09/data_test")
    val testInputPt2 = readInput("resources/day09/data_test_pt2")

    fun part1(input: List<String>): Int {

        val headPath = headPath(input)
        val tailPath: List<Point> = tailPath(headPath)

        val uniqueFields = tailPath.toSet()

        return uniqueFields.size
    }

    fun part2(input: List<String>): Int {

        val headPath = headPath(input)
        val path1: List<Point> = tailPath(headPath)
        val path2: List<Point> = tailPath(path1)
        val path3: List<Point> = tailPath(path2)
        val path4: List<Point> = tailPath(path3)
        val path5: List<Point> = tailPath(path4)
        val path6: List<Point> = tailPath(path5)
        val path7: List<Point> = tailPath(path6)
        val path8: List<Point> = tailPath(path7)
        val path9: List<Point> = tailPath(path8)

        val uniqueFields = path9.toSet()

        return uniqueFields.size
    }

    fun headPath(input: List<String>): List<Point> {

        var currentPosition = Point(0, 0)

        val headPath = mutableListOf(currentPosition)

        input.forEach {
            headPath.addAll(navigateBy(currentPosition, it))
            currentPosition = headPath.last()
        }

        return headPath
    }


    fun tailPath(headPath: List<Point>): List<Point> {
        var currentPosition = Point(0, 0)
        val tailPath = mutableListOf<Point>()

        headPath.forEach {
            currentPosition = followPosition(currentPosition, it)
            tailPath.add(currentPosition)
        }

        return tailPath
    }

    fun followPosition(cur: Point, new: Point): Point = when (getDirection(cur, new)) {
        Direction.LEFT -> Point(goLeft(cur, new), new.y)
        Direction.UPPER_LEFT -> Point(goLeft(cur, new), goUp(cur, new))
        Direction.UP -> Point(new.x, goUp(cur, new))
        Direction.UPPER_RIGHT -> Point(goRight(cur, new), goUp(cur, new))
        Direction.RIGHT -> Point(goRight(cur, new), new.y)
        Direction.LOWER_RIGHT -> Point(goRight(cur, new), goDown(cur, new))
        Direction.DOWN -> Point(new.x, goDown(cur, new))
        Direction.LOWER_LEFT -> Point(goLeft(cur, new), goDown(cur, new))
        Direction.NONE -> cur
    }

    private fun getDirection(cur: Point, new: Point) = when {
        horizontalEqual(cur, new) && verticalEqual(cur, new) -> Direction.NONE
        verticalTendency(new, cur) && goingUp(cur, new) -> Direction.UP
        verticalTendency(new, cur) -> Direction.DOWN
        horizontalTendency(new, cur) && goingRight(cur, new) -> Direction.RIGHT
        horizontalTendency(new, cur) -> Direction.LEFT
        goingRight(cur, new) && goingUp(cur, new) -> Direction.UPPER_RIGHT
        goingRight(cur, new) -> Direction.LOWER_RIGHT
        goingUp(cur, new) -> Direction.UPPER_LEFT
        else -> Direction.LOWER_LEFT
    }

    private fun verticalEqual(cur: Point, new: Point) = cur.y == new.y
    private fun horizontalEqual(cur: Point, new: Point) = cur.x == new.x
    private fun horizontalDifference(new: Point, cur: Point) = (new.x - cur.x).absoluteValue
    private fun verticalDifference(new: Point, cur: Point) = (new.y - cur.y).absoluteValue
    private fun goingRight(cur: Point, new: Point) = cur.x < new.x
    private fun goingUp(cur: Point, new: Point) = cur.y < new.y

    private fun horizontalTendency(new: Point, cur: Point) =
        horizontalDifference(new, cur) > verticalDifference(new, cur) || verticalEqual(cur, new)

    private fun verticalTendency(new: Point, cur: Point) =
        verticalDifference(new, cur) > horizontalDifference(new, cur) || horizontalEqual(cur, new)


    private fun goUp(cur: Point, new: Point) = if (cur.y < new.y - 1) new.y - 1 else cur.y
    private fun goDown(cur: Point, new: Point) = if (cur.y > new.y - 1) new.y + 1 else cur.y
    private fun goRight(cur: Point, new: Point) = if (cur.x < new.x - 1) new.x - 1 else cur.x
    private fun goLeft(cur: Point, new: Point) = if (cur.x > new.x + 1) new.x + 1 else cur.x

    enum class Direction {
        LEFT, UPPER_LEFT, UP, UPPER_RIGHT, RIGHT, LOWER_RIGHT, DOWN, LOWER_LEFT, NONE
    }

    private fun navigateBy(startingPoint: Point, navigationCommand: String): Collection<Point> {
        val path = mutableListOf<Point>()
        val (direction, length) = navigationCommand.split(" ")

        when (direction) {
            "R" -> for (step in 1..length.toInt()) {
                path.add(Point(startingPoint.x + step, startingPoint.y))
            }

            "U" -> for (step in 1..length.toInt()) {
                path.add(Point(startingPoint.x, startingPoint.y + step))
            }

            "L" -> for (step in -1 downTo -length.toInt()) {
                path.add(Point(startingPoint.x + step, startingPoint.y))
            }

            "D" -> for (step in -1 downTo -length.toInt()) {
                path.add(Point(startingPoint.x, startingPoint.y + step))
            }
        }

        return path
    }


    data class Point(
        val x: Int,
        val y: Int,
    )
}
