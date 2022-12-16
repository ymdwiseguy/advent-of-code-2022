package day08

import readInput

fun main() {
    println("Part 1: ${Day08().part1(Day08().input)}")
    println("Part 2: ${Day08().part2(Day08().input)}")
}

class Day08 {

    val input = readInput("resources/day08/data")
    val testInput = readInput("resources/day08/data_test")

    fun part1(input: List<String>): Int {
        var visibleCount = 0

        getTreesFromInput(input).forEach {
            if (it.isVisible()) visibleCount++
        }

        return visibleCount
    }

    fun part2(input: List<String>): Int {
        var maxScenicScore = 0
        var bestTree: Tree? = null
        getTreesFromInput(input).forEach { tree ->
            tree.scenicScore().let { score ->
                if (score > maxScenicScore) {
                    maxScenicScore = score
                    bestTree = tree
                }
            }
        }

        println("Best Tree: ${bestTree?.print()}")
        return maxScenicScore
    }

    private fun getTreesFromInput(input: List<String>): MutableList<Tree> {
        val rows: List<List<Int>> = input.map { string -> string.map { it.digitToInt() } }
        val columns = mutableMapOf<Int, MutableList<Int>>()

        rows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, treeHeight ->
                if (rowIndex == 0) {
                    columns[columnIndex] = mutableListOf(treeHeight)
                } else {
                    columns[columnIndex]?.add(treeHeight)
                }
            }
        }

        val trees = mutableListOf<Tree>()

        rows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, treeHeight ->
                val column = columns[columnIndex]
                val leftNeighbours = if (columnIndex == 0) emptyList() else row.slice(0 until columnIndex).reversed()
                val rightNeighbours =
                    if (columnIndex == row.lastIndex) emptyList() else row.slice(columnIndex + 1..row.lastIndex)
                val topNeighbours = if (rowIndex == 0) emptyList() else {
                    column?.slice(0 until rowIndex)?.reversed() ?: emptyList()
                }
                val bottomNeighbours =
                    if (rowIndex == column?.lastIndex) emptyList() else column?.slice(rowIndex + 1..(column.lastIndex))
                        ?: emptyList()
                trees.add(
                    Tree(
                        position = Position(rowIndex, columnIndex),
                        height = treeHeight,
                        leftNeighbours = leftNeighbours,
                        rightNeighbours = rightNeighbours,
                        topNeighbours = topNeighbours,
                        bottomNeighbours = bottomNeighbours,
                    )
                )
            }
        }
        return trees
    }

    data class Tree(
        val position: Position,
        val height: Int,
        val leftNeighbours: List<Int>,
        val rightNeighbours: List<Int>,
        val topNeighbours: List<Int>,
        val bottomNeighbours: List<Int>,
    ) {
        fun isVisible(): Boolean = leftNeighbours.isEmpty()
                || rightNeighbours.isEmpty()
                || topNeighbours.isEmpty()
                || bottomNeighbours.isEmpty()
                || height > leftNeighbours.maxOf { it }
                || height > rightNeighbours.maxOf { it }
                || height > topNeighbours.maxOf { it }
                || height > bottomNeighbours.maxOf { it }

        fun scenicScore(): Int {
            val leftScore = if (leftNeighbours.isEmpty()) 0 else leftNeighbours.viewDistance(height)
            val rightScore = if (rightNeighbours.isEmpty()) 0 else rightNeighbours.viewDistance(height)
            val topScore = if (topNeighbours.isEmpty()) 0 else topNeighbours.viewDistance(height)
            val bottomScore = if (bottomNeighbours.isEmpty()) 0 else bottomNeighbours.viewDistance(height)
            return leftScore * rightScore * topScore * bottomScore
        }

        fun print(): String {
            return toString() + " :: visible: ${isVisible()} :: scenic score: ${scenicScore()}"
        }
    }

}

fun List<Int>.viewDistance(height: Int): Int {
    var viewDistance = 0
    var blocked = false
    this.map {
        if (!blocked) {
            viewDistance++
            if (it >= height) blocked = true
        }
    }
    return viewDistance
}

typealias Position = Pair<Int, Int>
