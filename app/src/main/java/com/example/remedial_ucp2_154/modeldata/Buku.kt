package com.example.remedial_ucp2_154.modeldata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku")
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val penulis: String,
    val idKategori: Int?,
    val isDeleted: Boolean = false
)

@Entity(tableName = "buku_item")
data class BukuItem(
    @PrimaryKey
    val idFisik: String,
    val idBuku: Int,
    val status: String = "Tersedia",
    val isDeleted: Boolean = false
)