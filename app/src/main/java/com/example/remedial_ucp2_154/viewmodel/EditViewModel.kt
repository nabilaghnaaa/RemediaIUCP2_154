package com.example.remedial_ucp2_154.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedial_ucp2_154.repository.RepositoriBuku
import com.example.remedial_ucp2_154.view.route.DestinasiEdit
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriBuku: RepositoriBuku
) : ViewModel() {

    var bukuUiState by mutableStateOf(BukuUIState())
        private set

    private val bukuId: Int = checkNotNull(savedStateHandle[DestinasiEdit.BUKU_ID])

    init {
        viewModelScope.launch {
            bukuUiState = repositoriBuku.getBukuByIdStream(bukuId)
                .filterNotNull()
                .first()
                .toUiStateBuku(true)
        }
    }

    fun updateUiState(bukuEvent: BukuEvent) {
        bukuUiState = BukuUIState(
            bukuEvent = bukuEvent,
            isEntryValid = validasiInput(bukuEvent)
        )
    }

    private fun validasiInput(uiEvent: BukuEvent): Boolean {
        return with(uiEvent) {
            judul.isNotBlank() && penulis.isNotBlank() && idKategori != null
        }
    }

    suspend fun updateBuku() {
        if (validasiInput(bukuUiState.bukuEvent)) {
            repositoriBuku.updateBuku(bukuUiState.bukuEvent.toBuku())
        }
    }
}

// Fungsi extension untuk konversi dari Entity ke UI State
fun com.example.remedial_ucp2_154.modeldata.Buku.toUiStateBuku(isEntryValid: Boolean = false): BukuUIState = BukuUIState(
    bukuEvent = this.toBukuEvent(),
    isEntryValid = isEntryValid
)

fun com.example.remedial_ucp2_154.modeldata.Buku.toBukuEvent(): BukuEvent = BukuEvent(
    id = id,
    judul = judul,
    penulis = penulis,
    idKategori = idKategori
)