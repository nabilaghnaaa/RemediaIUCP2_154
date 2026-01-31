package com.example.remedial_ucp2_154.repository

import android.content.Context
import com.example.remedial_ucp2_154.modeldata.DatabaseBuku

interface InterfaceContainerApp {
    val repositoriBuku: RepositoriBuku
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoriBuku: RepositoriBuku by lazy {
        OfflineRepositoriBuku(DatabaseBuku.getDatabase(context).bukuDao())
    }
}