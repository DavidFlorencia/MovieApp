package com.dflorencia.movieapp.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.movieapp.api.Item
import com.dflorencia.movieapp.api.TmdbApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class OverviewViewModel: ViewModel() {

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
                _items.value = TmdbApi.retrofitService.getTopRatedMovies(apiKey).items
            }catch (e:Exception){
                _items.value = listOf()
            }
        }
    }

}