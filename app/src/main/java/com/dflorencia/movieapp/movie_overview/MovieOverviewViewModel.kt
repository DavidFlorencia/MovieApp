package com.dflorencia.movieapp.movie_overview

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
import java.lang.Exception
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class OverviewViewModel @Inject constructor(val tmdbApi: TmdbApi): ViewModel() {

    private val apiKey = "33d1fa5693faffec860d5568c417e32f"

    private val _status = MutableLiveData<ApiStatus>();
    val status: LiveData<ApiStatus> get() = _status;

    private val _moviePage = MutableLiveData<MoviePage>()
    val moviePage: LiveData<MoviePage> get() = _moviePage

    init {
        getApiItems()
    }

    private fun getApiItems() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING;
            try {
                _moviePage.value = tmdbApi.getTopRatedMovies(apiKey)
                _status.value = ApiStatus.DONE;
            }catch (e:Exception){
                _status.value = ApiStatus.ERROR;
                _moviePage.value = MoviePage()
            }
        }
    }

    fun displayItemDetails(movie: Movie) {
        Log.d("Prueba",movie.title.toString())
    }

}