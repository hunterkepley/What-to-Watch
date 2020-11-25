package com.main.whattowatch.repositories

import com.main.whattowatch.models.Movie
import com.main.whattowatch.parser.MovieParser
import org.springframework.stereotype.Repository

@Repository("inMemory")
class MovieRepository : IMovieRepository {
    private var moviesList: ArrayList<Movie>

    constructor() {
        val parser = MovieParser()
        parser.parseMovies()
        this.moviesList = parser.getMovies()
    }

    override fun getAllMovies(): ArrayList<Movie> {
        return moviesList
    }

}