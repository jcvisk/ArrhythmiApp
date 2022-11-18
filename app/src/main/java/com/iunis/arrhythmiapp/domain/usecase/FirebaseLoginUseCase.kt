package com.iunis.arrhythmiapp.domain.usecase

import com.iunis.arrhythmiapp.domain.repository.AuthRepository
import com.iunis.arrhythmiapp.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
    ) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val loggedSuccessfully = authRepository.login(email, password)
        if (loggedSuccessfully){
            emit(Resource.Success(true))
        }else{
            emit(Resource.Error("Login Error"))
        }
    }
}