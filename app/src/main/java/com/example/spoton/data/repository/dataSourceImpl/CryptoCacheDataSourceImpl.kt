package com.example.spoton.data.repository.dataSourceImpl

import com.example.spoton.data.model.Data
import com.example.spoton.data.repository.datasource.CryptoCacheDataSource
import javax.inject.Inject

class CryptoCacheDataSourceImpl @Inject constructor() : CryptoCacheDataSource {
    private var dataList = ArrayList<Data>()

    override suspend fun getCryptoFromCache(): List<Data> {
        return dataList
    }

    override suspend fun saveCryptoToCache(data: List<Data>) {
        dataList.clear()
        dataList = ArrayList(data)
    }

    override suspend fun addMoreDataToCache(data: List<Data>) {
        if (dataList != null && data != null) {
            dataList.addAll(data)
        }
    }
}
