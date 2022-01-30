package com.example.spoton.data.repository.dataSourceImpl

import com.example.spoton.data.api.CryptoService
import com.example.spoton.data.model.Crypto
import com.example.spoton.data.repository.datasource.CryptoRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class CryptoRemoteDataSourceImpl @Inject constructor(
    private val cryptoService: CryptoService,
) : CryptoRemoteDataSource {
    override suspend fun getData(limit: Int, offset: Int): Response<Crypto> {
        return cryptoService.getCryptoData(limit, offset)
    }
}
