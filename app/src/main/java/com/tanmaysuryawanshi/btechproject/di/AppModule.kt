package com.tanmaysuryawanshi.btechproject.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tanmaysuryawanshi.btechproject.data.network.WeatherApi
import com.tanmaysuryawanshi.btechproject.domain.repository.AuthRepository
import com.tanmaysuryawanshi.btechproject.domain.repository.AuthRepositoryImpl
import com.tanmaysuryawanshi.btechproject.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesFirebaseUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    @Provides
    @Singleton
    fun providesFirebaseFirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }



    @Provides
    @Singleton
    fun providesRespositoryImpl(firebaseAuth: FirebaseAuth):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }



}