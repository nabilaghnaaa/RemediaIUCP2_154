package com.example.remedial_ucp2_154.modeldata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Buku::class, BukuItem::class, Kategori::class, Audit::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseBuku : RoomDatabase() {
    abstract fun bukuDao(): BukuDao

    companion object {
        @Volatile
        private var Instance: DatabaseBuku? = null

        fun getDatabase(context: Context): DatabaseBuku {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseBuku::class.java,
                    "buku_database"
                ).build().also { Instance = it }
            }
        }
    }
}