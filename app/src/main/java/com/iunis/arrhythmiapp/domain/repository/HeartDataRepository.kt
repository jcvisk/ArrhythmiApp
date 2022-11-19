package com.iunis.arrhythmiapp.domain.repository

import com.iunis.arrhythmiapp.domain.model.HeartData
import com.iunis.arrhythmiapp.util.Resource

interface HeartDataRepository {

    suspend fun saveHeartData(heartData: HeartData): Resource<Unit>

    suspend fun getAllHeartData(): Resource<List<HeartData>>

    suspend fun deleteHeartData(heartData: HeartData): Resource<Unit>
}