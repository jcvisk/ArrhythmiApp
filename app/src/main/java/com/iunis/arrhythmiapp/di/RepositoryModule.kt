package com.iunis.arrhythmiapp.di

import com.iunis.arrhythmiapp.data.remote.FirebaseAuthRepositoryImpl
import com.iunis.arrhythmiapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: FirebaseAuthRepositoryImpl): AuthRepository
}