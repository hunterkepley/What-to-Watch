package main.whattowatch

fun main() {
    println("Hello World!")

    var parser = MovieParser()
    parser.parseMovies()
    parser.printMoviesRaw()
}
