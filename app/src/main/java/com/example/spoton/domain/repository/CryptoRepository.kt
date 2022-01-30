package com.example.spoton.domain

import com.example.spoton.data.model.Data

interface CryptoRepository {
    suspend fun getCryptoData(): List<Data>?
    suspend fun updateCryptoData(): List<Data>?
    suspend fun addMoreCryptoData(limit: Int, offset: Int): List<Data>?
}
