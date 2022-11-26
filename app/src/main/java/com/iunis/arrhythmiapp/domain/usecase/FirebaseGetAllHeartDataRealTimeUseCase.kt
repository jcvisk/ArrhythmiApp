package com.iunis.arrhythmiapp.domain.usecase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants.HEART_DATA_COLLECTION
import com.iunis.arrhythmiapp.domain.model.HeartData
import com.iunis.arrhythmiapp.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseGetAllHeartDataRealTimeUseCase @Inject constructor() {

    suspend operator fun invoke(): Flow<Resource<List<HeartData>>> = callbackFlow {

        val event = FirebaseFirestore.getInstance().collection(HEART_DATA_COLLECTION).orderBy("fecha", Query.Direction.DESCENDING)

        val subscription = event.addSnapshotListener { snapshot, error ->
            if (error != null) {
                this.trySend(Resource.Error(error.message.toString())).isSuccess
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val heartData = snapshot.toObjects(HeartData::class.java)
                this.trySend(Resource.Success(heartData)).isSuccess

            }
        }

        awaitClose { subscription.remove() }
    }
}