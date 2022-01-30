package com.example.spoton.data.repository.di

import com.example.spoton.data.repository.dataSourceImpl.CryptoLocalDataSourceImpl
import com.example.spoton.data.repository.datasource.CryptoLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoLocalDataModuleProvider {
    @Binds
    abstract fun bindCryptoLocalService(cryptoLocalDataSourceImpl: CryptoLocalDataSourceImpl): CryptoLocalDataSource
}