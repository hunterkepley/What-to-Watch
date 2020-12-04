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
import java.util.*

import java.util.Arrays.asList
import javax.servlet.http.HttpServletRequest


@Controller
class HomeController {
    private val movieService : MovieService
    var movieTitle : String = ""
    var message1: String = ""
    var message2: String = ""
    var chosenYear : String = ""
    val yearsBefore2000 = ArrayList<String>()

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

            if(years.contains(year) || year =="" || !filter.isNumber(year) ){}
            else if(year < "2000"){
                yearsBefore2000.add(year)
            }

            else{years.add(year)}


        }

        Collections.sort(years, Collections.reverseOrder())
        years.add("<2000")
        model.addAttribute("movieTitle", movieTitle)
        model.addAttribute("years", years)
        model.addAttribute("genres", genres)
        model.addAttribute("message1", message1)
        model.addAttribute("message2", message2)

        return "home"
    }

    @RequestMapping("",  method = arrayOf(RequestMethod.POST))
    public fun processForm(@RequestParam selectYear : String, selectGenre: Genre): String {
        if(selectYear == "<2000"){
            if(selectGenre != Genre.SCIENCEFICTION){
            var randomYear = (0..yearsBefore2000.size -1).random()
            var selectedYear = yearsBefore2000[randomYear]
            var filteredMovies = filter.filterByYear(filter.filterByGenre(movieService.getAllMovies(), selectGenre), selectedYear)
            var movies = filter.filterForeign(filteredMovies)

            while( movies.size == 0){
                randomYear = (0..yearsBefore2000.size -1).random()
                selectedYear = yearsBefore2000[randomYear]
                filteredMovies = filter.filterByYear(filter.filterByGenre(movieService.getAllMovies(), selectGenre), selectedYear)
                movies = filter.filterForeign(filteredMovies)
            }

                val movie = filter.pickRandomMovie(movies)
                movieTitle = movie.title

                message1= "We Found You a Movie!! "
                message2="Genre: $selectGenre, Year: $selectedYear"
            }
            else{
                message1 = "Sorry No Movie Found for $selectGenre in $selectYear!"
                message2 = "Try again."

            }}


        else {

            val filteredMovies = filter.filterByYear(filter.filterByGenre(movieService.getAllMovies(), selectGenre), selectYear)
            val movies = filter.filterForeign(filteredMovies)
            if( movies.size == 0){
                movieTitle=""
                message1 = "Sorry No Movie Found for $selectGenre in $selectYear!"
                message2 = "Try again."
            }
            else{
                val movie = filter.pickRandomMovie(movies)
                movieTitle = movie.title

                message1= "We Found You a Movie!! "
                message2="Genre: $selectGenre, Year: $selectYear"
            }
        }

        return "redirect:"
    }
}