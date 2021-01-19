package com.englishgalaxy.traveltestapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englishgalaxy.traveltestapp.net.NetRepository
import com.englishgalaxy.traveltestapp.net.responce.ItemPlaces
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MapViewModel(private val netRepository: NetRepository) : ViewModel() {

    private var _liveData = MutableLiveData<State>(State.Default)
    val liveData: LiveData<State> get() = _liveData

    fun getData() {
        viewModelScope.launch {
            _liveData.postValue(State.Loading)
            try {
                val res = netRepository.getItemPlaces()
                _liveData.postValue(State.Success(res))
            } catch (e: UnknownHostException) {
                _liveData.postValue(State.Error(e.message!!))
            }
        }
    }
}

sealed class State {
    object Default : State()
    object Loading : State()
    data class Success(val itemMap: ItemPlaces) : State()
    data class Error(val error: String) : State()
}