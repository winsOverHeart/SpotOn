package com.example.spoton.data.repository.datasource

import com.example.spoton.data.model.Data

interface CryptoCacheDataSource {
    suspend fun getCryptoFromCache(): List<Data>
    suspend fun saveCryptoToCache(data: List<Data>)
    suspend fun addMoreDataToCache(data: List<Data>)
}
