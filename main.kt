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
    var movies2016 = filterer.filterByYear(parser.movies, "2016")

    var moviesWar2016 = filterer.filterByGenre(movies2016, Genre.WAR)

    var moviesWar2016EN = filterer.filterForeign(moviesWar2016)

    for (n in 0 until moviesWar2016EN.size) {
        println(moviesWar2016EN[n].title)
    }
}
