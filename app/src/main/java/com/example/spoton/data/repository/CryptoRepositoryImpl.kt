package com.example.spoton.data.repository

import android.util.Log
import com.example.spoton.data.model.Data
import com.example.spoton.data.repository.datasource.CryptoCacheDataSource
import com.example.spoton.data.repository.datasource.CryptoLocalDataSource
import com.example.spoton.data.repository.datasource.CryptoRemoteDataSource
import com.example.spoton.domain.CryptoRepository
import java.lang.Exception
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoCacheDataSource: CryptoCacheDataSource,
    private val cryptoLocalDataSource: CryptoLocalDataSource,
    private val cryptoRemoteDataSource: CryptoRemoteDataSource
) : CryptoRepository {
    override suspend fun getCryptoData(): List<Data>? {
        return getCryptoDataFromCache()
    }

    override suspend fun updateCryptoData(): List<Data>? {
        val newListOfData = getCryptoDataFromAPI()
        cryptoLocalDataSource.clearAll()
        cryptoLocalDataSource.saveDataToDb(newListOfData)
        cryptoCacheDataSource.saveCryptoToCache(newListOfData)
        return newListOfData
    }

    override suspend fun addMoreCryptoData(limit: Int, offSet: Int): List<Data>? {
        val newListOfData = getCryptoDataFromAPI()
        cryptoLocalDataSource.saveDataToDb(newListOfData)
        cryptoCacheDataSource.addMoreDataToCache(newListOfData)
        return cryptoCacheDataSource.getCryptoFromCache()
    }

    private suspend fun getCryptoDataFromAPI(limit: Int = 20, offSet: Int = 0): List<Data> {
        lateinit var dataList: List<Data>
        try {
            val response = cryptoRemoteDataSource.getData(limit, offSet)
            val body = response.body()
            if (body != null) {
                dataList = body.data
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return dataList
    }

    private suspend fun getCryptoDataFromDB(): List<Data> {
        lateinit var dataList: List<Data>
        try {
            dataList = cryptoLocalDataSource.getDataFromDB()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (dataList.size > 0) {
            return dataList
        } else {
            dataList = getCryptoDataFromAPI()
            cryptoLocalDataSource.saveDataToDb(dataList)
        }

        return dataList
    }

    private suspend fun getCryptoDataFromCache(): List<Data> {
        lateinit var dataList: List<Data>
        try {
            dataList = cryptoCacheDataSource.getCryptoFromCache()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (dataList.isNotEmpty()) {
            return dataList
        } else {
            dataList = getCryptoDataFromDB()
            cryptoCacheDataSource.saveCryptoToCache(dataList)
        }

        return dataList
    }
}
