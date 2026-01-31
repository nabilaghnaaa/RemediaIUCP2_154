package com.example.remedial_ucp2_154.view.route

object DestinasiEdit : DestinasiNavigasi {
    override val route = "edit_buku"
    override val titleRes = "Perbarui Data Buku"
    const val BUKU_ID = "itemId"
    val routeWithArgs = "$route/{$BUKU_ID}"
}