package com.iunis.arrhythmiapp.domain.repository

import com.iunis.arrhythmiapp.domain.model.User

interface UserRepository {
    suspend fun createUser(user: User): Boolean

    suspend fun getUser(uid: String): User
}