package com.example.remedial_ucp2_154.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedial_ucp2_154.modeldata.Buku
import com.example.remedial_ucp2_154.repository.RepositoriBuku
import kotlinx.coroutines.flow.*

class HomeViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = repositoriBuku.getAllBukuStream()
        .map { HomeUiState(listBuku = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState()
        )
}

data class HomeUiState(val listBuku: List<Buku> = listOf())