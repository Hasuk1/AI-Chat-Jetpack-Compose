package com.example.aichat.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chat.AuthS
import com.feature.authentification.AuthenticationScreen

@Composable
fun AIChatNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AIChatAppScreens.AUTHSCREEN.route) {
      AuthenticationScreen{
        navController.navigate(AIChatAppScreens.CHATSCREEN.route){
          popUpTo(AIChatAppScreens.AUTHSCREEN.route){
            inclusive = true
          }
        }
      }
    }
    composable(AIChatAppScreens.CHATSCREEN.route) {
      AuthS(){

      }
    }
  }
}