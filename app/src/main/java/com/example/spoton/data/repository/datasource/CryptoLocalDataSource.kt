package com.example.spoton.data.repository.datasource

import com.example.spoton.data.model.Data

interface CryptoLocalDataSource {
    suspend fun getDataFromDB(): List<Data>
    suspend fun saveDataToDb(data: List<Data>)
    suspend fun clearAll()
}
