package com.example.something_calc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.utility.AppScreens
import com.example.something_calc.ui.SomethingCalcApp

@Composable
fun SomethingCalcNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AppScreens.MENU.route) {
      SomethingCalcApp(navController)
    }

  }
}