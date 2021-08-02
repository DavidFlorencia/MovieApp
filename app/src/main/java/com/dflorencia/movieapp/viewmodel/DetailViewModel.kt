package com.dflorencia.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.root.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val movieRepository: MovieRepository): ViewModel(){

    private val _movie = MutableLiveData<Movie>();
    val movie: LiveData<Movie> get() = _movie
    val trailerKey: LiveData<String> get() = movieRepository.key

    fun setMovie(movie: Movie){
        _movie.value = movie
    }

    fun refreshDataFromRepository(){
        viewModelScope.launch {
            movieRepository.refreshMovieTrailerKey(movie.value?.id.toString())
        }
    }

}