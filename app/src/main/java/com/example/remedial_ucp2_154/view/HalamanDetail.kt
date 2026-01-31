package com.example.remedial_ucp2_154.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedial_ucp2_154.view.route.DestinasiDetail
import com.example.remedial_ucp2_154.viewmodel.DetailViewModel
import com.example.remedial_ucp2_154.viewmodel.PenyediaViewModel

@Composable
fun HalamanDetail(
    onNavigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            BukuTopAppBar(title = DestinasiDetail.titleRes, canNavigateBack = true, navigateUp = onNavigateBack)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text("Detail Aset Buku Fisik", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Hapus Kategori (Cek Rollback)")
            }
        }
    }
}