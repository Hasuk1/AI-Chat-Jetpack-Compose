package com.hassuk1.aichat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hassuk1.aichat.PartiallySelectableText
import com.hassuk1.feature.authentication.AuthenticationScreen

@Composable
fun AIChatNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AIChatAppScreens.AUTHSCREEN.route) {
      AuthenticationScreen {
        navController.navigate(AIChatAppScreens.CHATSCREEN.route) {
          popUpTo(AIChatAppScreens.AUTHSCREEN.route) {
            inclusive = true
          }
        }
      }

    }
    composable(AIChatAppScreens.CHATSCREEN.route) {
      PartiallySelectableText()
    }
  }
}