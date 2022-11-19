package com.iunis.arrhythmiapp.di

import com.iunis.arrhythmiapp.data.remote.FirebaseAuthRepositoryImpl
import com.iunis.arrhythmiapp.data.remote.FirestoreHeartDataRepositoryImpl
import com.iunis.arrhythmiapp.data.remote.FirestoreUserRepositoryImpl
import com.iunis.arrhythmiapp.domain.repository.AuthRepository
import com.iunis.arrhythmiapp.domain.repository.HeartDataRepository
import com.iunis.arrhythmiapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHeartDataRepository(heartDataRepository: FirestoreHeartDataRepositoryImpl): HeartDataRepository

    @Binds
    abstract fun bindAuthRepository(authRepository: FirebaseAuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindUserRepository(userRepository: FirestoreUserRepositoryImpl): UserRepository
}