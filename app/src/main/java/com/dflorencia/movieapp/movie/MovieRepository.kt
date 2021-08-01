package com.dflorencia.movieapp.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.api.TmdbApi
import com.dflorencia.movieapp.database.MovieDao
import com.dflorencia.movieapp.root.asApiModel
import com.dflorencia.movieapp.root.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDao: MovieDao,
                                          private val tmdbApi: TmdbApi){

    private val apiKey = "33d1fa5693faffec860d5568c417e32f"

    suspend fun refreshMovies(){
        withContext(Dispatchers.IO){
            val response = tmdbApi.getTopRatedMovies(apiKey)
            response.movies?.asDatabaseModel()?.let {
                movieDao.clear()
                movieDao.insertAll(it)
            }
        }
    }

    val movies: LiveData<List<Movie>> = Transformations.map(movieDao.getMovies()){
        it.asApiModel()
    }
}