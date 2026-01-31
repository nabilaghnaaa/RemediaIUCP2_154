package com.example.remedial_ucp2_154.view.route

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_buku"
    override val titleRes = "Detail Aset Buku"
    const val BUKU_ID = "itemId"
    val routeWithArgs = "$route/{$BUKU_ID}"
}