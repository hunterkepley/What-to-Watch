package main.whattowatch

class MovieFilter {

    fun filterByYear(movies: ArrayList<Movie>, year: String): ArrayList<Movie> {
        val filtered = ArrayList<Movie>()
        for(m in 0 until movies.size) {
            var releaseYear = movies[m].releaseDate.split("-").toTypedArray()[0]
            if (releaseYear == year) {
                filtered.add(movies[m])
            }
        }

        return filtered
    }
}
