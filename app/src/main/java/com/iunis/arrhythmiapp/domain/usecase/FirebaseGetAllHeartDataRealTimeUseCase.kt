package com.iunis.arrhythmiapp.domain.usecase

import com.google.firebase.firestore.FirebaseFirestore
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants.HEART_DATA_COLLECTION
import com.iunis.arrhythmiapp.domain.model.HeartData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseGetAllHeartDataRealTimeUseCase @Inject constructor() {

    suspend operator fun invoke(): Flow<List<HeartData>> = callbackFlow {
        val event = FirebaseFirestore.getInstance().collection(HEART_DATA_COLLECTION)
    }
}