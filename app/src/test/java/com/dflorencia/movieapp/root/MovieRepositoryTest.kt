package com.dflorencia.movieapp.root

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.api.MoviePage
import com.dflorencia.movieapp.api.MovieTrailer
import com.dflorencia.movieapp.api.MovieTrailerPage
import com.dflorencia.movieapp.data.source.MovieDaoFake
import com.dflorencia.movieapp.data.source.TmdbApiFake
import com.dflorencia.movieapp.database.MovieEntity
import com.dflorencia.movieapp.getOrAwaitValue
import com.dflorencia.movieapp.ui.Filter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest{
    private val movie1 = MovieEntity(id = 1, title = "Movie1", overview = "Summary1")
    private val movie2 = MovieEntity(id = 2, title = "Movie2", overview = "Summary2")
    private val movie3 = MovieEntity(id = 3, title = "Movie3", overview = "Summary3")
    private val movieList = listOf(movie1,movie2,movie3)
    private val moviePage = MoviePage()
    private val movies = MutableLiveData(movieList)
    private val movieTrailerPage = MovieTrailerPage()
    private val movieTrailer = MovieTrailer(key = "abcdef")

    private lateinit var tmdbApi: TmdbApiFake
    private lateinit var movieDao: MovieDaoFake

    // Class under test
    private lateinit var movieRepository: MovieRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository(){
        moviePage.movies = movieList.asApiModel()
        movieTrailerPage.movieTrailers = listOf(movieTrailer)
        tmdbApi = TmdbApiFake(moviePage, movieTrailerPage)
        movieDao = MovieDaoFake(movies)

        movieRepository = MovieRepository(movieDao,tmdbApi,"apiKey")
    }

    @Test
    fun getMovies_refreshTopRated_verifyReturnedMovies() = runBlocking{
        // When refresh movies with filter TOP RATED
        movieRepository.refreshMovies(Filter.TOP_RATED,"")

        val value:List<Movie> = movieRepository.movies.getOrAwaitValue()

        // Then movies value is updated
        assertThat(value, IsEqual(movies.value?.asApiModel()))
    }
}