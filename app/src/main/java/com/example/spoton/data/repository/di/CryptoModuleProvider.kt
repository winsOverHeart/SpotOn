package com.example.spoton.data.repository.di

import com.example.spoton.data.api.CryptoService
import com.example.spoton.data.repository.CryptoRepositoryImpl
import com.example.spoton.domain.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoModuleProvider {
    @Binds
    abstract fun bindCryptoServiceService(cryptoRepositoryImpl: CryptoRepositoryImpl): CryptoRepository
}