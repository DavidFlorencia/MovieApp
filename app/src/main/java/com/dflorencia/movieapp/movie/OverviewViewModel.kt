package com.dflorencia.movieapp.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.api.MoviePage
import com.dflorencia.movieapp.api.TmdbApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class OverviewViewModel @Inject constructor(val movieRepository: MovieRepository): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>();
    val status: LiveData<ApiStatus> get() = _status;

//    private lateinit var currentMoviePage: MoviePage
//    private val _movies = MutableLiveData<List<Movie>>();
    val movies: LiveData<List<Movie>> get() = movieRepository.movies

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                movieRepository.refreshMovies()
                _status.value = ApiStatus.DONE
            } catch (networkError: IOException) {
                if (movies.value.isNullOrEmpty()) {
                    _status.value = ApiStatus.ERROR
                }
            }
        }
    }


/*    private fun getApiItems() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                currentMoviePage = tmdbApi.getTopRatedMovies(apiKey)
                _movies.value = currentMoviePage.movies
                _status.value = ApiStatus.DONE
            }catch (e:Exception){
                _status.value = ApiStatus.ERROR
                currentMoviePage = MoviePage()
            }
        }
    }*/

/*    fun displayItemDetails(movie: Movie) {
        Log.d("Prueba",movie.title.toString())
    }*/

}