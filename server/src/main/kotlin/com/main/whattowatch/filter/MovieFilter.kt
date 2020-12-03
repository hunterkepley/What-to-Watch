package com.main.whattowatch.filter

import com.main.whattowatch.models.Movie
import com.main.whattowatch.models.Genre

class MovieFilter {

    // Filters movies by a specific year and returns the updated ArrayList
    fun filterByYear(movies: ArrayList<Movie>, year: String): ArrayList<Movie> {
        val filtered = ArrayList<Movie>()
        for(m in 0 until movies.size) {
            var releaseYear = movies[m].releaseDate.split("-").toTypedArray()[0]
            if (releaseYear == year) {
                filtered.add(movies[m])
            }
        }
        return filtered
    }

    // Filters movies by English/non-English
    fun filterForeign(movies: ArrayList<Movie>): ArrayList<Movie> {
        val filtered = ArrayList<Movie>()

        for (i in 0 until movies.size) {
            if (movies[i].language == "en") {
                filtered.add(movies[i])
            }
        }
        return filtered
    }

    fun filterByGenre(movies: ArrayList<Movie>, genre: Genre): ArrayList<Movie> {
        var genreString = ""
        val filtered = ArrayList<Movie>()

        when(genre) {
            Genre.SCIENCEFICTION -> genreString = "ScienceFiction"
            Genre.COMEDY -> genreString = "Comedy"
            Genre.DOCUMENTARY -> genreString = "Documentary"
            Genre.DRAMA -> genreString = "Drama"
            Genre.FAMILY -> genreString = "Family"
            Genre.ACTION -> genreString = "Action"
            Genre.WESTERN -> genreString = "Western"
            Genre.WAR -> genreString = "War"
            Genre.CRIME -> genreString = "Crime"
            Genre.FANTASY -> genreString = "Fantasy"
            Genre.ADVENTURE -> genreString = "Adventure"
            Genre.HORROR -> genreString = "Horror"
            Genre.THRILLER -> genreString = "Thriller"
            Genre.ROMANCE -> genreString = "Romance"
            Genre.ANIMATION -> genreString = "Animation"
        }

        for (i in 0 until movies.size) {
            for (g in 0 until movies[i].genres.size) {
                if (movies[i].genres[g] == genreString) {
                    filtered.add(movies[i])
                }
            }
        }

        return filtered
    }

    fun pickRandomMovie(movies: ArrayList<Movie>): Movie {
        val randomMovie = (0..movies.size).random()

        return movies[randomMovie]
    }
}
