package com.dflorencia.movieapp.root

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dflorencia.movieapp.api.Keys
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.api.MoviePage
import com.dflorencia.movieapp.api.TmdbApi
import com.dflorencia.movieapp.database.MovieDao
import com.dflorencia.movieapp.ui.Filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDao: MovieDao,
                                          private val tmdbApi: TmdbApi,
                                          private val apiKey: String){

//    private val apiKey = Keys.apiKey()

    suspend fun refreshMovies(filter: Filter, query:String) {
        withContext(Dispatchers.IO){
            val response = when(filter){
                Filter.TOP_RATED -> tmdbApi.getTopRatedMovies(apiKey)
                Filter.POPULAR -> tmdbApi.getPopularMovies(apiKey)
                Filter.SEARCH -> tmdbApi.searchMoviesFromNetwork(apiKey, query)
                else -> MoviePage()
            }
            response.movies?.asDatabaseModel()?.let {
                movieDao.clear()
                movieDao.insertAll(it)
            }
        }
    }

    private val _key = MutableLiveData<String>();
    val key: LiveData<String> get() = _key

    suspend fun refreshMovieTrailerKey(movieId:String) {
        val movieTrailerPage = tmdbApi.getMovieTrailers(movieId,apiKey)
        _key.value = movieTrailerPage.movieTrailers?.get(0)?.key.toString()
    }

    val movies: LiveData<List<Movie>> = Transformations.map(movieDao.getMovies()){
        it.asApiModel()
    }
}