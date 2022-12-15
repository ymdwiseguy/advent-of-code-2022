package day07

import day07.Day07.*
import day07.Day07.Command.*
import readInput

fun main() {
    println("Part 1: ${Day07().part1(Day07().input)}")
    println("Part 2: ${Day07().part2(Day07().input)}")
}

class Day07 {

    val input = readInput("resources/day07/data")
    val testInput = readInput("resources/day07/data_test")

    fun part1(input: List<String>): Int {
        val (directoryTree, directories) = getDirectories(input)

        val directorySizes = directories.sumOf {
            val dirSize = directoryTree.gotoDir(it)?.directorySize() ?: 0
            return@sumOf if (dirSize <= 100000) dirSize else 0
        }

        return directorySizes
    }

    fun part2(input: List<String>): Int {
        val (directoryTree, directories) = getDirectories(input)
        val spaceToFree = 30000000 - (70000000 - directoryTree.directorySize())

        var perfectDirSize = Int.MAX_VALUE

        directories.forEach {
            val dirSize = directoryTree.gotoDir(it)?.directorySize() ?: 0
            if (dirSize in spaceToFree..perfectDirSize) {
                perfectDirSize = dirSize
            }
        }

        return perfectDirSize
    }


    private fun getDirectories(input: List<String>): Pair<MutableMap<String, Directory>, MutableList<List<String>>> {
        val directoryTree = mutableMapOf("/" to Directory())
        val directories = mutableListOf(listOf("/"))
        val currentPath = mutableListOf<String>()
        val commands = getCommands(input)

        commands.forEach {
            directoryTree.parseCommand(it, currentPath, directories)
        }
        return Pair(directoryTree, directories)
    }


    fun getCommands(input: List<String>): List<Command> = input.map {
        when {
            it.startsWith("$ cd ..") -> NavigateUp
            it.startsWith("$ cd") -> ChangeDirectory(
                it.substring(5..it.lastIndex)
            )

            it.startsWith("$ ls") -> ListContent
            it.startsWith("dir ") -> AddDirectory(
                it.substring(4..it.lastIndex)
            )

            else -> {
                val parts = it.split(" ")
                return@map AddFile(parts.last(), parts.first().toInt())
            }
        }
    }

    sealed class Command {
        data class ChangeDirectory(
            val which: String
        ) : Command()

        object ListContent : Command()
        data class AddDirectory(
            val name: String
        ) : Command()

        data class AddFile(
            val name: String,
            val size: Int,
        ) : Command()

        object NavigateUp : Command()
    }

    data class Directory(
        val subDirectories: MutableMap<String, Directory> = mutableMapOf(),
        val files: MutableList<File> = mutableListOf(),
    ) {
        fun directorySize(): Int =
            files.sumOf { it.size } + subDirectories.values.sumOf { directory -> directory.directorySize() }

        override fun toString(): String {
            return "${subDirectories.print()} " + files.map { it.toString() }
        }
    }

    data class File(
        val name: String,
        val size: Int,
    ) {
        override fun toString(): String = "$name :: $size"
    }
}

fun MutableMap<String, Directory>.print(): String = this.map {
    "${it.key}: ${it.value}\n"
}.joinToString("\n")

fun MutableMap<String, Directory>.directorySize(): Int =
    this.values.sumOf { it.directorySize() }

fun MutableMap<String, Directory>.parseCommand(
    command: Command,
    currentPath: MutableList<String>,
    directories: MutableList<List<String>>
) {
    when (command) {
        is AddDirectory -> {
            directories.add(currentPath.toList() + listOf(command.name))
            gotoDir(currentPath.toList())?.subDirectories?.put(command.name, Directory())
        }

        is AddFile -> {
            val file = File(command.name, command.size)
            gotoDir(currentPath)?.files?.add(file)
        }

        is ChangeDirectory -> {
            currentPath.add(command.which)
        }

        NavigateUp -> currentPath.removeLast()
        else -> Unit
    }
}

private fun MutableMap<String, Directory>.gotoDir(currentPath: List<String>): Directory? =
    levelX(currentPath, currentPath.size)

private fun MutableMap<String, Directory>.level1(currentPath: List<String>) =
    this[currentPath[0]]

private fun MutableMap<String, Directory>.level2(currentPath: List<String>) =
    this[currentPath[0]]?.subDirectories?.get(currentPath[1])

private fun MutableMap<String, Directory>.levelX(currentPath: List<String>, level: Int): Directory? {
    return when {
        level == 1 -> level1(currentPath)
        level == 2 -> level2(currentPath)
        level > 2 -> levelX(currentPath, (level - 1))?.subDirectories?.get(currentPath[level - 1])
        else -> TODO()
    }
}
