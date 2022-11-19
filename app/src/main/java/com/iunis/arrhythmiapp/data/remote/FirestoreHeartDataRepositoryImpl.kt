package com.iunis.arrhythmiapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants.HEART_DATA_COLLECTION
import com.iunis.arrhythmiapp.domain.model.HeartData
import com.iunis.arrhythmiapp.domain.model.User
import com.iunis.arrhythmiapp.domain.repository.HeartDataRepository
import com.iunis.arrhythmiapp.util.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreHeartDataRepositoryImpl @Inject constructor():   HeartDataRepository{

    override suspend fun saveHeartData(heartData: HeartData): Resource<Unit> {
        return try {
            var isSuccessFul = false
            FirebaseFirestore.getInstance().collection(HEART_DATA_COLLECTION)
                .document(heartData.id)
                .set(heartData, SetOptions.merge())
                .addOnCompleteListener {
                    isSuccessFul = it.isSuccessful
                }
                .await()

            if (isSuccessFul){
                Resource.Success(Unit)
            }else{
                Resource.Error("Network Error")
            }
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getAllHeartData(): Resource<List<HeartData>> {
        return try {
            val heartDataList = FirebaseFirestore.getInstance().collection(HEART_DATA_COLLECTION)
                .get()
                .await()
                .toObjects(HeartData::class.java)

            Resource.Success(heartDataList)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun deleteHeartData(heartData: HeartData): Resource<Unit> {
        return try {
            var isSuccessFul = false
            FirebaseFirestore.getInstance().collection(HEART_DATA_COLLECTION)
                .document(heartData.id)
                .delete()
                .addOnCompleteListener {
                    isSuccessFul = it.isSuccessful
                }
                .await()

            if (isSuccessFul){
                Resource.Success(Unit)
            }else{
                Resource.Error("Network Error")
            }

        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }
}