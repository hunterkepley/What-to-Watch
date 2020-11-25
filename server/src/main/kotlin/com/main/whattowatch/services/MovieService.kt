package com.main.whattowatch.services
import com.main.whattowatch.models.Movie
import com.main.whattowatch.repositories.IMovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class MovieService {
    private val movieRepo: IMovieRepository
    @Autowired
    constructor(@Qualifier("inMemory")movieRepo: IMovieRepository) {//Qualifier is only used for interfaces
        this.movieRepo = movieRepo
    }

    fun getAllMovies(): ArrayList<Movie> {
        return movieRepo.getAllMovies()
    }

//    fun getRandomMovie(): ArrayList<Movie> {
//        val randomMovie = movieRepo.getAllMovies()
//    }

}