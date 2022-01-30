package com.example.spoton

import com.example.spoton.data.model.Data
import com.example.spoton.domain.CryptoRepository

class FakeCryptoRepository : CryptoRepository {
    private val datas = mutableListOf<Data>()

    init {
        datas.add(
            Data(
                "1.0", "overview1", "path1", "date1", "title3",
                "", "", "",
                "", "", "", ""
            )
        )
        datas.add(
            Data(
                "2.0", "overview2", "path2", "date2", "title4", "", "", "",
                "", "", "", ""
            )
        )
    }

    override suspend fun getCryptoData(): List<Data>? {
        return datas
    }

    override suspend fun updateCryptoData(): List<Data>? {
        datas.clear()
        datas.add(
            Data(
                "3.0", "overview3", "path3", "date3", "title3", "", "", "",
                "", "", "", ""
            )
        )
        datas.add(
            Data(
                "3.0", "overview4", "path4", "date4", "title4", "", "", "",
                "", "", "", ""
            )
        )
        return datas
    }

    override suspend fun addMoreCryptoData(limit: Int, offset: Int): List<Data>? {
        TODO("Not yet implemented")
    }
}
