package main.whattowatch

fun main() {
    println("Hello World!")

    var parser = MovieParser()
    parser.parseMovies() // Parses CSV into Kotlin
    parser.printMovieNames()
    parser.printMovies()
}
