package com.example.remedial_ucp2_154.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remedial_ucp2_154.repository.AplikasiBuku

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer untuk Halaman Home
        initializer {
            HomeViewModel(aplikasiBuku().container.repositoriBuku)
        }

        // Initializer untuk Halaman Entry (Tambah Buku)
        initializer {
            EntryViewModel(aplikasiBuku().container.repositoriBuku)
        }

        // Initializer untuk Halaman Detail (Mekanisme Rollback)
        initializer {
            DetailViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                repositoriBuku = aplikasiBuku().container.repositoriBuku
            )
        }

        // Initializer untuk Halaman Edit (Migrasi Data & Update)
        initializer {
            EditViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                repositoriBuku = aplikasiBuku().container.repositoriBuku
            )
        }
    }
}

// Fungsi extension untuk memudahkan akses ke container repository dari Application class
fun CreationExtras.aplikasiBuku(): AplikasiBuku =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiBuku)