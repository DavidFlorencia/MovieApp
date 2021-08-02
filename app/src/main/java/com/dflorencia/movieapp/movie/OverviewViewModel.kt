package com.dflorencia.movieapp.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.movieapp.api.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }
enum class Filter { TOP_RATED, POPULAR, SEARCH, CACHE}

@HiltViewModel
class OverviewViewModel @Inject constructor(val movieRepository: MovieRepository): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status;

    private var lastFilter = Filter.TOP_RATED
    private val _filter = MutableLiveData(Filter.TOP_RATED)
    val filter: LiveData<Filter> get() = _filter

    val movies: LiveData<List<Movie>> get() = movieRepository.movies

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository(query: String = "") {
        viewModelScope.launch {
            setStatus(ApiStatus.LOADING)
            try {
                filter.value?.let { movieRepository.refreshMovies(it,query) }
                setStatus(ApiStatus.DONE)
            } catch (networkError: IOException) {
                if (movies.value.isNullOrEmpty()) {
                    setStatus(ApiStatus.ERROR)
                }else {
                    if (filter.value == Filter.SEARCH){
                        Log.d("TestAnnotation","Filter cache movies")
                    }
                    setStatus(ApiStatus.DONE)
                }
                _filter.value = Filter.CACHE
            }
        }
    }

    private suspend fun setStatus(status: ApiStatus) {
        _status.value = status
    }

    fun setFilter(filter: Filter, query: String = "") {
        _filter.value = filter
        refreshDataFromRepository(query)
        if (filter != Filter.SEARCH) lastFilter = filter
    }

    fun setLastFilter() {
        _filter.value = lastFilter
        refreshDataFromRepository()
    }
}