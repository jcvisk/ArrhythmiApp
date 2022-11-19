package com.iunis.arrhythmiapp.domain.usecase

import com.iunis.arrhythmiapp.domain.model.HeartData
import com.iunis.arrhythmiapp.domain.repository.HeartDataRepository
import com.iunis.arrhythmiapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseGetAllHeartDataUseCase @Inject constructor(
    private val heartDataRepository: HeartDataRepository){

    suspend operator fun invoke():Flow<Resource<List<HeartData>>> = flow{
        emit(Resource.Loading)

        val networkRequest = heartDataRepository.getAllHeartData()

        when(networkRequest){
            is Resource.Success -> emit(Resource.Success(networkRequest.data))
            is Resource.Error -> emit(Resource.Error(networkRequest.message))
            else -> Resource.Finished
        }
    }
}