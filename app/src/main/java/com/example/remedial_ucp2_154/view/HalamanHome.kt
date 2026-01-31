package com.example.remedial_ucp2_154.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedial_ucp2_154.view.route.DestinasiHome
import com.example.remedial_ucp2_154.viewmodel.HomeViewModel
import com.example.remedial_ucp2_154.viewmodel.PenyediaViewModel

@Composable
fun HalamanHome(
    onNavigateToEntry: () -> Unit,
    onDetailClick: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            BukuTopAppBar(title = DestinasiHome.titleRes, canNavigateBack = false)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToEntry) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { innerPadding ->
        if (homeUiState.listBuku.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                Text("Belum ada data buku.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(homeUiState.listBuku) { buku ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onDetailClick(buku.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = buku.judul, style = MaterialTheme.typography.titleLarge)
                            Text(text = "Penulis: ${buku.penulis}")
                        }
                    }
                }
            }
        }
    }
}