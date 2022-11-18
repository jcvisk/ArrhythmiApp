package com.iunis.arrhythmiapp.domain.usecase

import com.iunis.arrhythmiapp.domain.model.User
import com.iunis.arrhythmiapp.domain.repository.AuthRepository
import com.iunis.arrhythmiapp.domain.repository.UserRepository
import com.iunis.arrhythmiapp.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
    ) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        val userUID = authRepository.login(email, password)
        if (userUID.isNotEmpty()){
            val user = userRepository.getUser(uid = userUID)
            emit(Resource.Success(user))
            emit(Resource.Finished)
        }else{
            emit(Resource.Error("Login Error"))
            emit(Resource.Finished)
        }
    }
}