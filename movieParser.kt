package main.whattowatch

import java.io.File
import java.util.Arrays

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

    // Prints each movie name
    fun printMovieNames() {
        for(m in 0 until list.size) {
            val arr = list[m].split(",").toTypedArray()

            for(n in 0 until arr.size) {
                if (arr[n].contains("tt") && arr.size > n+2 && arr[n].length == 9) {
                    println(arr[n+2])
                }
            }
        }
    }
    

    fun printNumCommas() {
        // This is printing the number of commas in each movie entry
        list.forEach {
            l -> println("${l.count{ c -> c == 'a' }}")
        }
    }
}
