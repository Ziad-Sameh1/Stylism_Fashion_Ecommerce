package com.example.stylism_fashion_ecommerce.feature_login.di

import com.example.stylism_fashion_ecommerce.feature_login.data.repository.AuthRepoImpl
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideAuthRepo(firebaseAuth: FirebaseAuth): AuthRepo {
        return AuthRepoImpl(firebaseAuth = firebaseAuth)
    }
}