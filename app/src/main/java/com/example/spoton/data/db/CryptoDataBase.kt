package com.example.spoton.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spoton.data.model.Data

@Database(
    entities = [Data::class],
    version = 1,
    exportSchema = false
)
abstract class CryptoDataBase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}
