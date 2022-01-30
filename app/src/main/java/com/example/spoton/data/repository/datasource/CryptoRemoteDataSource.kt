package com.example.spoton.data.repository.datasource

import com.example.spoton.data.model.Crypto
import retrofit2.Response

interface CryptoRemoteDataSource {
    suspend fun getData(limit: Int, offset: Int): Response<Crypto>
}
