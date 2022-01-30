package com.example.spoton.domain.usecase

import com.example.spoton.data.model.Data
import com.example.spoton.domain.CryptoRepository
import javax.inject.Inject

class UpdateCryptoUsecase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    suspend fun execute(): List<Data>? = cryptoRepository.updateCryptoData()
}
