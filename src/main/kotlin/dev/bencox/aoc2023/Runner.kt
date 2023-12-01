package dev.bencox.aoc2023

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        Runner().runOne(args[0])
    } else {
        Runner().runAll()
    }
}

class Runner {

    fun runAll() {
        println("Running all exercises")
        println()
        (1..24).mapNotNull { day ->
            val className = "dev.bencox.aoc2023.Day $day"
            try {
                val kClass = Class.forName(className)
                kClass.getDeclaredConstructor().newInstance() as Exercise<*>
            } catch (e: ClassNotFoundException) {
                null
            }
        }.forEach {
            println(it.run())
        }

    }

    fun runOne(day: Any) {
        println("Running exercise for Day $day")
        println()
        val className = "dev.bencox.aoc2023.Day $day"
        try {
            val kClass = Class.forName(className)
            val exercise = kClass.getDeclaredConstructor().newInstance() as Exercise<*>
            println(exercise.run())
        } catch (e: ClassNotFoundException) {
            throw Exception("Unable to find an exercise class called $className")
        }
    }

}