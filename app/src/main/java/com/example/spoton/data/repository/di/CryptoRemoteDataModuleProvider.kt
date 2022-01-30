package com.example.spoton.data.repository.di

import com.example.spoton.data.repository.dataSourceImpl.CryptoRemoteDataSourceImpl
import com.example.spoton.data.repository.datasource.CryptoRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoRemoteDataModuleProvider {
    @Binds
    abstract fun bindCryptoRemoteService(cryptoRemote: CryptoRemoteDataSourceImpl): CryptoRemoteDataSource
}
