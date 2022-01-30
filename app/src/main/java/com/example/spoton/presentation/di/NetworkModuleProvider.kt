package com.example.spoton.presentation.di

import com.example.spoton.data.api.CryptoService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleProvider {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coincap.io")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    fun createCryptoService(retrofit: Retrofit): CryptoService {
        return retrofit.create(CryptoService::class.java)
    }
}
