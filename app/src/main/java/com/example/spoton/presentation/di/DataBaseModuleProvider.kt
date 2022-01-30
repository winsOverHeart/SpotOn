package com.example.spoton.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.spoton.data.db.CryptoDao
import com.example.spoton.data.db.CryptoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModuleProvider {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CryptoDataBase {
        return Room.databaseBuilder(
            appContext,
            CryptoDataBase::class.java,
            "SampleDb"
        ).build()
    }

    @Provides
    fun provideChannelDao(cryptoDataBase: CryptoDataBase): CryptoDao {
        return cryptoDataBase.cryptoDao()
    }
}