package com.bangkit.wisatabanten

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkit.wisatabanten.ui.component.BottomBar
import com.bangkit.wisatabanten.ui.navigation.Screen
import com.bangkit.wisatabanten.ui.navigation.SetupNavGraph
import com.bangkit.wisatabanten.ui.theme.WisataBantenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetTourApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailTour.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        SetupNavGraph(modifier = modifier.padding(innerPadding), navController = navController)
    }
}

@Composable
@Preview(showBackground = true)
fun JetRecipeAppPreview() {
    WisataBantenTheme() {
        JetTourApp()
    }
}