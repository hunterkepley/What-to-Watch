package main.whattowatch

import java.io.File


class MovieParser {

    val list = arrayListOf("")

    fun parseMovies() {
        println("\nparsing movies...")

        var filePath = "movies/movies_metadata.csv"

        // Read movie metadata CSV and save each line in list
        try{
            var input:List<String> = File(filePath).readLines()
            input.forEach { 
                l -> list.add(l)
            }
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            println("\n...finished parsing movies")
        }
    }

    fun printMoviesRaw() {
        // This is printing the number of commas in each movie entry
        list.forEach {
            l -> println("$l\n\n")
            //l -> println("${l.count{ c -> c == 'a' }}")
        }
    }

    fun printNumCommas() {
        // This is printing the number of commas in each movie entry
        list.forEach {
            //l -> println("$l\n\n")
            l -> println("${l.count{ c -> c == 'a' }}")
        }
    }
}
