package com.dflorencia.movieapp.overview

import android.util.Log
import androidx.hilt.Assisted
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.movieapp.api.Item
import com.dflorencia.movieapp.api.TmdbApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(val tmdbApi: TmdbApi): ViewModel() {

    private val apiKey = "33d1fa5693faffec860d5568c417e32f"

    private val _response = MutableLiveData<String>();
    val response: LiveData<String> get() = _response;

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        getApiItems()
    }

    private fun getApiItems() {
        viewModelScope.launch {
            try {
                _items.value = tmdbApi.getTopRatedMovies(apiKey).items
                Log.d("Prueba",items.value?.size.toString())
            }catch (e:Exception){
                _items.value = listOf()
                Log.d("Prueba",e.toString())
            }
        }
    }

}