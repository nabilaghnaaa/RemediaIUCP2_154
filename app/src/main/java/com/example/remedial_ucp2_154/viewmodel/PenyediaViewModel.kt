package com.example.remedial_ucp2_154.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remedial_ucp2_154.repository.AplikasiBuku

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiBuku().container.repositoriBuku) }
        initializer { EntryViewModel(aplikasiBuku().container.repositoriBuku) }
        initializer {
            DetailViewModel(this.createSavedStateHandle(), aplikasiBuku().container.repositoriBuku)
        }
    }
}

fun CreationExtras.aplikasiBuku(): AplikasiBuku =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiBuku)