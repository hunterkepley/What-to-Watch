package main.whattowatch


fun main() {
    println("Hello World!")

    var parser = MovieParser()
    var filterer = MovieFilter()
    parser.parseMovies() // Parses CSV into Kotlin
    println("Press enter to continue...")
    readLine()
    parser.printMovies()
    println("Press enter to continue...")
    readLine()
    var movies2016 = filterer.filterByYear(parser.movies, "1999")
    for (n in 1 until movies2016.size) {
        println(movies2016[n].title)
    }
}
