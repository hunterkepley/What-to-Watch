package main.whattowatch

import java.io.File
import java.util.Arrays

class Movie {
    var title = ""
    var language = ""
}

class MovieParser {

    val list = arrayListOf("")
    val movieTitles = arrayListOf("")
    val movieLanguages = arrayListOf("")
    val movies = ArrayList<Movie>()

    fun String.onlyLetters() = all { it.isLetter() }

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

        // Do the actual parsing
        for(m in 0 until list.size) {
            val arr = list[m].split(",").toTypedArray()

            var movie = Movie()

            for(n in 0 until arr.size) {
                // Movie Title
                if (arr[n].contains("tt") && arr.size > n+2 && arr[n].length == 9) {
                    movieTitles.add(arr[n+2])
                    movie.title = arr[n+2]
                }

                // Movie Language
                if (arr[n].length == 2 && arr[n].onlyLetters()) {
                    movie.language = arr[n]
                }
            }
            movies.add(movie)
        }
    }

    // Prints each movie name
    fun printMovieNames() {
        movieTitles.forEach {
            t -> print("$t\n")
        }
    }
    

    fun printNumCommas() {
        // This is printing the number of commas in each movie entry
        list.forEach {
            l -> println("${l.count{ c -> c == 'a' }}")
        }
    }

    fun printMovies() {
        movies.forEach {
            m -> println("Title: ${m.title}; Language: ${m.language}")
        }
    }
}
