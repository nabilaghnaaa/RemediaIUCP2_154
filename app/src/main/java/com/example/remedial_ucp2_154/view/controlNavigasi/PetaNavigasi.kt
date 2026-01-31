package com.example.remedial_ucp2_154.view.controlNavigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remedial_ucp2_154.view.HalamanHome
import com.example.remedial_ucp2_154.view.HalamanEntry
import com.example.remedial_ucp2_154.view.HalamanDetail
import com.example.remedial_ucp2_154.view.HalamanEdit
import com.example.remedial_ucp2_154.view.route.DestinasiHome
import com.example.remedial_ucp2_154.view.route.DestinasiEntry
import com.example.remedial_ucp2_154.view.route.DestinasiDetail
import com.example.remedial_ucp2_154.view.route.DestinasiEdit

@Composable
fun PetaNavigasi(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HalamanHome(
                onNavigateToEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { idBuku ->
                    navController.navigate("${DestinasiDetail.route}/$idBuku")
                }
            )
        }

        composable(DestinasiEntry.route) {
            HalamanEntry(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.BUKU_ID) {
                type = NavType.IntType
            })
        ) {
            HalamanDetail(
                onNavigateBack = { navController.popBackStack() },
                onEditClick = { idBuku ->
                    navController.navigate("${DestinasiEdit.route}/$idBuku")
                }
            )
        }

        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.BUKU_ID) {
                type = NavType.IntType
            })
        ) {
            HalamanEdit(
                onNavigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}