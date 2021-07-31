package com.dflorencia.movieapp.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.movieapp.api.Item
import com.dflorencia.movieapp.api.Page
import com.dflorencia.movieapp.api.TmdbApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(val tmdbApi: TmdbApi): ViewModel() {

    private val apiKey = "33d1fa5693faffec860d5568c417e32f"

    private val _pageInfo = MutableLiveData<Page>()
    val pageInfo: LiveData<Page> get() = _pageInfo

    init {
        getApiItems()
    }

    private fun getApiItems() {
        viewModelScope.launch {
            try {
                _pageInfo.value = tmdbApi.getTopRatedMovies(apiKey)
            }catch (e:Exception){
                _pageInfo.value = Page()
            }
        }
    }

    fun displayItemDetails(item: Item) {
        Log.d("Prueba",item.title.toString())
    }

}