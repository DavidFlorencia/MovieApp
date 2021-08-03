package com.dflorencia.movieapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dflorencia.movieapp.database.MovieDao
import com.dflorencia.movieapp.database.MovieEntity

class MovieDaoFake(var movies: MutableLiveData<List<MovieEntity>> = MutableLiveData(listOf())): MovieDao {

    override fun getMovies(): LiveData<List<MovieEntity>> = movies

    override fun insertAll(videos: List<MovieEntity>) {
        movies.value = videos
    }

    override fun clear() {
        movies = MutableLiveData(listOf())
    }
}