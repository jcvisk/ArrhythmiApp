package com.iunis.arrhythmiapp.domain.repository

import com.iunis.arrhythmiapp.util.Resource

interface AuthRepository {
    suspend fun login(email:String, password:String): Boolean
    suspend fun signUp(email:String, password: String): Boolean
}