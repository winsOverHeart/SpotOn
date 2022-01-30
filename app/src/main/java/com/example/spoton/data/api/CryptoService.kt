package com.example.spoton.data.api

import com.example.spoton.data.model.Crypto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoService {
    @GET("v2/assets?")
    suspend fun getCryptoData(
        @Query("limit") limit: Int,@Query(value = "offset") offset: Int
    ): Response<Crypto>
}
