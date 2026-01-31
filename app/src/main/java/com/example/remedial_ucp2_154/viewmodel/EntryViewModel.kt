package com.example.remedial_ucp2_154.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.remedial_ucp2_154.modeldata.Buku
import com.example.remedial_ucp2_154.repository.RepositoriBuku

class EntryViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {
    var uiStateBuku by mutableStateOf(BukuUIState())
        private set

    fun updateUiState(bukuEvent: BukuEvent) {
        uiStateBuku = BukuUIState(
            bukuEvent = bukuEvent,
            isEntryValid = validasiInput(bukuEvent)
        )
    }

    // Validasi tipe data ketat
    private fun validasiInput(uiEvent: BukuEvent): Boolean {
        return with(uiEvent) {
            judul.isNotBlank() && penulis.isNotBlank() && idKategori != null
        }
    }

    suspend fun saveBuku() {
        if (validasiInput(uiStateBuku.bukuEvent)) {
            repositoriBuku.insertBuku(uiStateBuku.bukuEvent.toBuku())
        }
    }
}

data class BukuUIState(
    val bukuEvent: BukuEvent = BukuEvent(),
    val isEntryValid: Boolean = false
)

data class BukuEvent(
    val id: Int = 0,
    val judul: String = "",
    val penulis: String = "",
    val idKategori: Int? = null
)

fun BukuEvent.toBuku(): Buku = Buku(id = id, judul = judul, penulis = penulis, idKategori = idKategori)