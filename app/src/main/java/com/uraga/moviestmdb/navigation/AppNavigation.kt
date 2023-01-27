package com.uraga.moviestmdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uraga.moviestmdb.presentation.movies_list.MovieListScreen
import com.uraga.moviestmdb.presentation.splash.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen.route
    ) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(AppScreen.MovieListScreen.route) {
            MovieListScreen(navController = navController)
        }
    }
}