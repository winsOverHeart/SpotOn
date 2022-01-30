package com.example.spoton.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spoton.data.model.Data

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCrypto(data: List<Data>)

    @Query("DELETE FROM crypto_data")
    suspend fun deleteAllCrypto()

    @Query("SELECT * FROM crypto_data")
    suspend fun getData(): List<Data>
}
