package day2

data class NavigationCommand(
   val direction: Direction,
   val howFar: Int,
) {

    constructor(s: String) : this(
        Direction.valueOf(s.split(" ").first().uppercase()),
        s.split(" ").last().toInt(),
    )

}


enum class Direction{
    FORWARD,
    DOWN,
    UP,
}