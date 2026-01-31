package com.example.remedial_ucp2_154.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedial_ucp2_154.view.route.DestinasiEntry
import com.example.remedial_ucp2_154.viewmodel.EntryViewModel
import com.example.remedial_ucp2_154.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HalamanEntry(
    onNavigateBack: () -> Unit,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiStateBuku

    Scaffold(
        topBar = {
            BukuTopAppBar(title = DestinasiEntry.titleRes, canNavigateBack = true, navigateUp = onNavigateBack)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            OutlinedTextField(
                value = uiState.bukuEvent.judul,
                onValueChange = { viewModel.updateUiState(uiState.bukuEvent.copy(judul = it)) },
                label = { Text("Judul Buku") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = uiState.bukuEvent.penulis,
                onValueChange = { viewModel.updateUiState(uiState.bukuEvent.copy(penulis = it)) },
                label = { Text("Penulis") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveBuku()
                        onNavigateBack()
                    }
                },
                enabled = uiState.isEntryValid,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Simpan Data")
            }
        }
    }
}