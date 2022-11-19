package com.iunis.arrhythmiapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iunis.arrhythmiapp.domain.model.HeartData
import com.iunis.arrhythmiapp.domain.usecase.FirebaseAddHeartDataUseCase
import com.iunis.arrhythmiapp.domain.usecase.FirebaseDeleteHeartDataUseCase
import com.iunis.arrhythmiapp.domain.usecase.FirebaseGetAllHeartDataUseCase
import com.iunis.arrhythmiapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addHeartDataUseCase: FirebaseAddHeartDataUseCase,
    private val deleteHeartDataUseCase: FirebaseDeleteHeartDataUseCase,
    private val getAllHeartDataUseCase: FirebaseGetAllHeartDataUseCase
): ViewModel(){

    private val _heartDataListState: MutableLiveData<Resource<List<HeartData>>> = MutableLiveData()
    val heartDataListState: LiveData<Resource<List<HeartData>>>
        get() = _heartDataListState

    private val _addHeartDataState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val addHeartDataState: LiveData<Resource<Unit>>
        get() = _addHeartDataState

    private val _deleteHeartDataState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val deleteHeartDataState: LiveData<Resource<Unit>>
        get() = _deleteHeartDataState

    fun getAllHeartData(){
        viewModelScope.launch {
            getAllHeartDataUseCase().onEach {
                _heartDataListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun saveHeartData(heartData: HeartData) {
        viewModelScope.launch {
            addHeartDataUseCase(heartData).onEach {
                _addHeartDataState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun deleteHeartData(heartData: HeartData) {
        viewModelScope.launch {
            deleteHeartDataUseCase(heartData).onEach {
                _deleteHeartDataState.value = it
            }.launchIn(viewModelScope)
        }
    }

}