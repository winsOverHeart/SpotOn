package com.example.spoton.domain.usecase

import com.example.spoton.data.model.Data
import com.example.spoton.domain.CryptoRepository
import javax.inject.Inject

class GetCryptoDataUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    suspend fun execute(): List<Data>? = cryptoRepository.getCryptoData()
}
