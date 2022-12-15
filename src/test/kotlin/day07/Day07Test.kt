package day07

import day07.Day07.Command.*
import day07.Day07.Directory
import day07.Day07.File
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shouldBe

internal class Day07Test {

    private lateinit var day: Day07
    private val testInput: List<String> = Day07().testInput
    private val input: List<String> = Day07().input

    @BeforeEach
    fun setUp() {
        day = Day07()
    }

    @Test
    fun `part 1 should return 95437`() {
        day.part1(testInput) shouldBe 95437
    }

    @Test
    fun `part 1 with real data should be greater than 120994`() {
        day.part1(input) shouldBe 1390824
    }

    @Test
    fun `part 2 should return 24933642`() {
        day.part2(testInput) shouldBe 24933642
    }

    @Test
    fun `part 2 with real data should return 7490863`() {
        day.part2(input) shouldBe 7490863
    }

    @Test
    fun `get commands from string`() {
        day.getCommands(
            listOf(
                "$ cd /",
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat",
                "dir d",
                "$ cd a",
                "$ ls",
                "dir e",
                "29116 f",
                "2557 g",
                "62596 h.lst",
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd ..",
                "$ cd ..",
                "$ cd d",
                "$ ls",
                "4060174 j",
                "8033020 d.log",
                "5626152 d.ext",
                "7214296 k",
            )
        ) shouldBe listOf(
            ChangeDirectory("/"),
            ListContent,
            AddDirectory("a"),
            AddFile("b.txt", 14848514),
            AddFile("c.dat", 8504156),
            AddDirectory("d"),
            ChangeDirectory("a"),
            ListContent,
            AddDirectory("e"),
            AddFile("f", 29116),
            AddFile("g", 2557),
            AddFile("h.lst", 62596),
            ChangeDirectory("e"),
            ListContent,
            AddFile("i", 584),
            NavigateUp,
            NavigateUp,
            ChangeDirectory("d"),
            ListContent,
            AddFile("j", 4060174),
            AddFile("d.log", 8033020),
            AddFile("d.ext", 5626152),
            AddFile("k", 7214296),
        )
    }

    @Test
    fun `parse all commands`() {
        val directoryTree = mutableMapOf("/" to Directory())
        val directories = mutableListOf<List<String>>(listOf("/"))
        val currentPath = mutableListOf<String>()

        val commands = listOf(
            ChangeDirectory("/"),
            ListContent,
            AddDirectory("a"),
            AddFile("b.txt", 14848514),
            AddFile("c.dat", 8504156),
            AddDirectory("d"),
            ChangeDirectory("a"),
            ListContent,
            AddDirectory("e"),
            AddFile("f", 29116),
            AddFile("g", 2557),
            AddFile("h.lst", 62596),
            ChangeDirectory("e"),
            ListContent,
            AddFile("i", 584),
            NavigateUp,
            NavigateUp,
            ChangeDirectory("d"),
            ListContent,
            AddFile("j", 4060174),
            AddFile("d.log", 8033020),
            AddFile("d.ext", 5626152),
            AddFile("k", 7214296),
        )

        commands.forEach {
            directoryTree.parseCommand(it, currentPath, directories)
        }

        currentPath shouldBe mutableListOf("/", "d")
        directoryTree shouldBe mutableMapOf(
            "/" to Directory(
                subDirectories = mutableMapOf(
                    "a" to Directory(
                        subDirectories = mutableMapOf(
                            "e" to Directory(
                                files = mutableListOf(
                                    File("i", 584)
                                )
                            )
                        ),
                        files = mutableListOf(
                            File("f", 29116),
                            File("g", 2557),
                            File("h.lst", 62596),
                        )
                    ),
                    "d" to Directory(
                        files = mutableListOf(
                            File("j", 4060174),
                            File("d.log", 8033020),
                            File("d.ext", 5626152),
                            File("k", 7214296),
                        ),
                    ),
                ),
                files = mutableListOf(
                    File("b.txt", 14848514),
                    File("c.dat", 8504156),
                )
            )
        )

        directories shouldBe mutableListOf(
            listOf("/"),
            listOf("/", "a"),
            listOf("/", "d"),
            listOf("/", "a", "e"),
        )

        directoryTree.directorySize() shouldBe 48381165
    }

    @Test
    fun `get directory size`() {
        Directory(
            subDirectories = mutableMapOf(
                "c" to Directory(
                    files = mutableListOf(
                        File("d", 20)
                    )
                )
            ),
            files = mutableListOf(
                File("a", 5),
                File("b", 5),

                )
        ).directorySize() shouldBe 30
    }

    @Test
    fun `get directory size for whole tree`() {
        mutableMapOf(
            "/" to Directory(
                subDirectories = mutableMapOf(
                    "a" to Directory(
                        subDirectories = mutableMapOf(
                            "e" to Directory(
                                files = mutableListOf(
                                    File("i", 584)
                                )
                            )
                        ),
                        files = mutableListOf(
                            File("f", 29116),
                            File("g", 2557),
                            File("h.lst", 62596),
                        )
                    ),
                    "d" to Directory(
                        files = mutableListOf(
                            File("j", 4060174),
                            File("d.log", 8033020),
                            File("d.ext", 5626152),
                            File("k", 7214296),
                        ),
                    ),
                ),
                files = mutableListOf(
                    File("b.txt", 14848514),
                    File("c.dat", 8504156),
                )
            )
        ).directorySize() shouldBe 48381165
    }
}

