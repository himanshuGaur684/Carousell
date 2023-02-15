package com.example.carousell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carousell.navigation.AppNavGraph
import com.example.carousell.navigation.NavigationProvider
import com.example.carousell.ui.theme.CarousellTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navGraphProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarousellTheme {
                val navController = rememberNavController()
                ArticleApp(navGraphProvider = navGraphProvider, navHostController = navController)
            }
        }
    }
}

@Composable
fun ArticleApp(navGraphProvider: NavigationProvider, navHostController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        AppNavGraph(navController =navHostController, navGraProvider =navGraphProvider)
    }
}