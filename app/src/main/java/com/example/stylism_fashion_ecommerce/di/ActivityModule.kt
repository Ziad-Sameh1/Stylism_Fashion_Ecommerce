package com.example.stylism_fashion_ecommerce.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.stylism_fashion_ecommerce.data.repository.DatabaseRepoImpl
import com.example.stylism_fashion_ecommerce.domain.repository.DatabaseRepo
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(CONSTANTS.MY_PREFERENCES, MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideEditedPreference(@ApplicationContext context: Context): SharedPreferences.Editor {
        return context.getSharedPreferences(CONSTANTS.MY_PREFERENCES, MODE_PRIVATE).edit()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseUser(firebaseAuth: FirebaseAuth): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideDatabaseRepo(firebaseFirestore: FirebaseFirestore): DatabaseRepo {
        return DatabaseRepoImpl(firebaseFirestore = firebaseFirestore)
    }
}