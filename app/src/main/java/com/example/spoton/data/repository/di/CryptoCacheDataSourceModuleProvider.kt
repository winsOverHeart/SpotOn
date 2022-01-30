package com.example.spoton.data.repository.di

import com.example.spoton.data.repository.CryptoRepositoryImpl
import com.example.spoton.data.repository.dataSourceImpl.CryptoCacheDataSourceImpl
import com.example.spoton.data.repository.datasource.CryptoCacheDataSource
import com.example.spoton.domain.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoCacheDataSourceModuleProvider {
    @Binds
    abstract fun bindCryptoCacheService(cryptoCacheDataSourceImpl: CryptoCacheDataSourceImpl): CryptoCacheDataSource
}