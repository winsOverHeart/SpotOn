package com.example.spoton.data.repository.dataSourceImpl

import com.example.spoton.data.db.CryptoDao
import com.example.spoton.data.model.Data
import com.example.spoton.data.repository.datasource.CryptoLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CryptoLocalDataSourceImpl @Inject constructor(private val cryptoDao: CryptoDao) : CryptoLocalDataSource {

    override suspend fun getDataFromDB(): List<Data> {
        return cryptoDao.getData()
    }

    override suspend fun saveDataToDb(data: List<Data>) {
        CoroutineScope(Dispatchers.IO).launch {
            cryptoDao.saveCrypto(data)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            cryptoDao.deleteAllCrypto()
        }
    }
}
