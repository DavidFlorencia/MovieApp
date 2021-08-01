package com.dflorencia.movieapp.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dflorencia.movieapp.api.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel(){

    private val _movie = MutableLiveData<Movie>();
    val movie: LiveData<Movie> get() = _movie;

    fun setMovie(movie: Movie){
        _movie.value = movie
    }
}