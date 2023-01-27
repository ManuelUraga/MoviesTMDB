package com.uraga.moviestmdb.navigation

sealed class AppScreen (val route: String) {
    object SplashScreen: AppScreen(route = "splash_screen")
    object MovieListScreen: AppScreen(route = "movies_list_screen")
}