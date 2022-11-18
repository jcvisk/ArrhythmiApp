package com.iunis.arrhythmiapp.presentation.password_recovery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iunis.arrhythmiapp.domain.usecase.FirebasePasswordRecoveryUseCase
import com.iunis.arrhythmiapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val passwordRecoveryUseCase: FirebasePasswordRecoveryUseCase
): ViewModel(){

    private val _passwordSend: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val passwordSend: LiveData<Resource<Boolean>>
        get() = _passwordSend

    fun sendPasswordLink(email: String){
        viewModelScope.launch {
            passwordRecoveryUseCase(email).onEach {
                _passwordSend.value = it
            }.launchIn(viewModelScope)
        }
    }
}