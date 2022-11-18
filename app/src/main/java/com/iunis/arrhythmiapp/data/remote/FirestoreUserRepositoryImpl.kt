package com.iunis.arrhythmiapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants.USERS_COLLECTION
import com.iunis.arrhythmiapp.domain.model.User
import com.iunis.arrhythmiapp.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreUserRepositoryImpl @Inject constructor(): UserRepository{

    /**
     * Metodo para crear un usuario en FireStore
     */
    override suspend fun createUser(user: User): Boolean {
        return try {
            var isSuccessFul = false
            FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
                .document(user.uid)
                .set(user, SetOptions.merge())
                .addOnCompleteListener {
                    isSuccessFul = it.isSuccessful
                }
                .await()
            isSuccessFul
        }catch (e:Exception){
            false
        }
    }

    /**
     * Metodo para obtener un usuario en FireStore
     */
    override suspend fun getUser(uid: String): User {
        return try {
            var loggedUser = User()
            FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
                .document(uid)
                .get()
                .addOnSuccessListener {
                    loggedUser = it.toObject(User::class.java)!!
                }
                .await()
            loggedUser
        }catch (e:Exception){
            User()
        }
    }
}