package com.main.whattowatch.controllers

import com.main.whattowatch.models.Movie
import com.main.whattowatch.services.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieController {
    private val movieService : MovieService

    @Autowired
    constructor(movieService: MovieService) {
        this.movieService = movieService
    }

    @GetMapping("")
    fun getHome(): ArrayList<Movie> {
        return movieService.getAllMovies()
    }
}
