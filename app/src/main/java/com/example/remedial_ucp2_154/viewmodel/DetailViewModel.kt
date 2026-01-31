package com.example.remedial_ucp2_154.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedial_ucp2_154.modeldata.Kategori
import com.example.remedial_ucp2_154.repository.RepositoriBuku
import com.example.remedial_ucp2_154.view.route.DestinasiDetail
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriBuku: RepositoriBuku
) : ViewModel() {
    private val bukuId: Int = checkNotNull(savedStateHandle[DestinasiDetail.BUKU_ID])

    fun hapusKategoriAman(kategori: Kategori, opsi: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Menjalankan mekanisme rollback otomatis melalui Database Transaction
                repositoriBuku.safeDeleteKategori(kategori, opsi)
            } catch (e: Exception) {
                onError(e.message ?: "Gagal: Status buku masih dipinjam")
            }
        }
    }
}