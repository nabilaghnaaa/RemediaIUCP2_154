package com.example.remedial_ucp2_154.modeldata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_log")
data class Audit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaTabel: String,
    val aksi: String,
    val dataLama: String?,
    val dataBaru: String?,
    val timestamp: Long = System.currentTimeMillis()
)