package com.main.whattowatch.controllers

import com.main.whattowatch.filter.MovieFilter
import com.main.whattowatch.models.Genre
import com.main.whattowatch.services.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestParam

import java.util.ArrayList
import java.util.Arrays.asList
import javax.servlet.http.HttpServletRequest


@Controller
class HomeController {
    private val movieService : MovieService
    var movieTitle : String = ""
    var message: String = ""
    var chosenYear : String = ""

    val filter = MovieFilter()
    @Autowired
    constructor(movieService: MovieService) {
        this.movieService = movieService
    }



    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    public fun home(model: Model): String {
        val allMovies = movieService.getAllMovies()

        val genres = ArrayList<Genre>()
        val years = ArrayList<String>()



        for(genre in  Genre.values()){
            genres.add(genre)
        }

        for (i in allMovies){
            val year = i.releaseDate.split("-").toTypedArray()[0]
            if(years.contains(year) || year =="" || !filter.isNumber(year) ||year<"2000"){}
            else{years.add(year)}


        }
        years.sort()
        model.addAttribute("movieTitle", movieTitle)
        model.addAttribute("title", "What to Watch?")
        model.addAttribute("years", years)
        model.addAttribute("genres", genres)
        model.addAttribute("message", message)

        return "home"
    }

    @RequestMapping("",  method = arrayOf(RequestMethod.POST))
    public fun processForm(@RequestParam selectYear : String, selectGenre: Genre): String {

        val filteredMovies = filter.filterByYear(filter.filterByGenre(movieService.getAllMovies(), selectGenre), selectYear)
        val movies = filter.filterForeign(filteredMovies)
        if( movies.size == 0){
            movieTitle=""
            message = "Sorry No Movie Found for $selectGenre in $selectYear! Try again."
        }
        else{
            val movie = filter.pickRandomMovie(movies)
            movieTitle = movie.title

            message= "We Found You a Movie! Genre: $selectGenre Year: $selectYear"

        }
        return "redirect:"
    }
}