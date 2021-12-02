package day2

data class Postion(
    var horizontal: Int,
    var depth: Int,
    var aim: Int,
) {

    fun navigate(command: NavigationCommand) = when(command.direction){
        Direction.FORWARD -> horizontal += command.howFar
        Direction.DOWN -> depth += command.howFar
        Direction.UP -> depth -= command.howFar
    }

    fun navigateForAim(command: NavigationCommand) = when(command.direction){
        Direction.FORWARD -> {
            horizontal += command.howFar
            depth += (aim * command.howFar)
        }
        Direction.DOWN -> aim += command.howFar
        Direction.UP -> aim -= command.howFar
    }

    fun finalPosition(): Int = horizontal * depth
}