package com.example.remedial_ucp2_154.modeldata

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BukuDao {
    @Query("SELECT * FROM buku WHERE isDeleted = 0")
    fun getAllBuku(): Flow<List<Buku>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuku(buku: Buku)

    @Update
    suspend fun updateBuku(buku: Buku)

    @Query("SELECT * FROM kategori")
    fun getAllKategori(): Flow<List<Kategori>>

    @Insert
    suspend fun insertKategori(kategori: Kategori)

    @Delete
    suspend fun deleteKategori(kategori: Kategori)

    @Query("UPDATE buku SET idKategori = NULL WHERE idKategori = :idKategori")
    suspend fun updateBukuToTanpaKategori(idKategori: Int)

    @Query("SELECT COUNT(*) FROM buku_item WHERE idBuku IN (SELECT id FROM buku WHERE idKategori = :idKategori) AND status = 'Dipinjam'")
    suspend fun countBukuDipinjam(idKategori: Int): Int

    @Insert
    suspend fun insertAudit(audit: Audit)
}