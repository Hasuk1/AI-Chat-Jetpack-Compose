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
import com.feature.authentification.ScaffoldExample

@Composable
fun AIChatNavHost(navController: NavHostController, startRoute: String) {
  val context = LocalContext.current
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AIChatAppScreens.AUTHSCREEN.route) {
//      ScaffoldExample(){
//        navController.navigate(AIChatAppScreens.CHATSCREEN.route)
//      }
      AuthenticationScreen()
    }
    composable(AIChatAppScreens.CHATSCREEN.route) {
      AuthS(){
        context.startActivity(
          Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/Hasuk1")
          )
        )
      }
    }
  }
}