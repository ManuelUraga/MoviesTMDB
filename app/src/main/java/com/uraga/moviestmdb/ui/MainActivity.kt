package com.uraga.moviestmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.uraga.moviestmdb.navigation.AppNavigation
import com.uraga.moviestmdb.ui.theme.MoviesTMDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTMDBTheme(darkTheme = true) {
                val navController = rememberNavController()
                Surface {
                    AppNavigation(navController)
                }
            }
        }
    }
}