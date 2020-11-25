package com.main.whattowatch.parser
import com.main.whattowatch.models.Movie
import java.io.File
import java.util.Arrays


class MovieParser {

    private val list = arrayListOf("")
    private val movies = ArrayList<Movie>()

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

            for(n in 1 until arr.size) {

                if (arr[n].contains("tt") && arr.size > n+3 && arr[n].length == 9) { // Title
                    movie.title = arr[n+2]
                } else if (arr[n].length == 2 && arr[n].onlyLetters()) { // Language
                    movie.language = arr[n]
                } else if (arr[n].length == 10 && arr[n].contains("-")) { // Release date & runtime
                    movie.releaseDate = arr[n] // Release date
                    if (arr[n+2].length <= 6) {
                        movie.runTime = arr[n+2] // Runtime
                    }
                } else if (arr[n].contains("'name': ") and arr[n-1].contains("'id'")) {
                    var genreVal = arr[n].split(":")
                    var genre: String
                    if (!genreVal[1].substring(1, genreVal[1].length-1).contains(" ")) {
                        var filters = "'\"]} "
                        genre = genreVal[1].filterNot { filters.indexOf(it) > -1 }
                        movie.genres.add(genre)
                    }
                }
            }
            if (movie.genres.size == 0) {
                movie.genres.add("Unknown")
            }
            movies.add(movie)
        }
    }

    // Prints each movie name
    fun printMovieTitles() {
        movies.forEach {
            t -> print("${t.title}\n")
        }
    }

    fun printNumCommas() {
        // This is printing the number of commas in each movie entry
        list.forEach {
            l -> println("${l.count{ c -> c == ',' }}")
        }
    }

    fun getMovies(): ArrayList<Movie> {
        return movies
    }

    fun printMovies() {
        movies.forEach {
            m -> println("Title: ${m.title}; Genres: ${m.genres.joinToString()}; Language: ${m.language}; Release Date: ${m.releaseDate}; Runtime: ${m.runTime}m\n")
        }
    }
}
