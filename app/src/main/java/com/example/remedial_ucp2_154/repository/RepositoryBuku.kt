package com.example.remedial_ucp2_154.repository

import com.example.remedial_ucp2_154.modeldata.Buku
import com.example.remedial_ucp2_154.modeldata.BukuDao
import com.example.remedial_ucp2_154.modeldata.Kategori
import com.example.remedial_ucp2_154.modeldata.Audit
import kotlinx.coroutines.flow.Flow

interface RepositoriBuku {
    fun getAllBukuStream(): Flow<List<Buku>>
    fun getAllKategoriStream(): Flow<List<Kategori>>
    fun getBukuByIdStream(id: Int): Flow<Buku?>
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(buku: Buku)
    suspend fun insertKategori(kategori: Kategori)

    suspend fun safeDeleteKategori(kategori: Kategori, opsiDinamis: String)
    suspend fun softDeleteBuku(buku: Buku)
}

class OfflineRepositoriBuku(private val bukuDao: BukuDao) : RepositoriBuku {
    override fun getAllBukuStream(): Flow<List<Buku>> = bukuDao.getAllBuku()
    override fun getAllKategoriStream(): Flow<List<Kategori>> = bukuDao.getAllKategori()
    override fun getBukuByIdStream(id: Int): Flow<Buku?> = bukuDao.getBukuById(id)

    override suspend fun insertBuku(buku: Buku) {
        bukuDao.insertBuku(buku)
        bukuDao.insertAudit(Audit(namaTabel = "buku", aksi = "INSERT", dataLama = null, dataBaru = buku.toString()))
    }

    override suspend fun updateBuku(buku: Buku) = bukuDao.updateBuku(buku)
    override suspend fun insertKategori(kategori: Kategori) = bukuDao.insertKategori(kategori)

    override suspend fun softDeleteBuku(buku: Buku) {
        val bukuDeleted = buku.copy(isDeleted = true)
        bukuDao.updateBuku(bukuDeleted)
    }

    override suspend fun safeDeleteKategori(kategori: Kategori, opsiDinamis: String) {
        val pinjamCount = bukuDao.countBukuDipinjam(kategori.id)

        if (pinjamCount > 0) {
            throw Exception("Gagal! $pinjamCount buku dalam kategori ini sedang dipinjam.")
        } else {
            if (opsiDinamis == "PINDAHKAN") {
                bukuDao.updateBukuToTanpaKategori(kategori.id)
            } else if (opsiDinamis == "HAPUS_LOGIS") {
            }
            bukuDao.deleteKategori(kategori)
        }
    }
}