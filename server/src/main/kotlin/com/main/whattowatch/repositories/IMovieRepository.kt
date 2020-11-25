package com.main.whattowatch.repositories

import com.main.whattowatch.models.Movie

interface IMovieRepository {
    //movie repository interface (Dao: data access object)
    fun getAllMovies(): ArrayList<Movie>
}