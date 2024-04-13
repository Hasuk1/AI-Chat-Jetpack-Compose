package com.hassuk1.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.hassuk1.aichat.navigation.AIChatAppScreens
import com.hassuk1.aichat.navigation.AIChatNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      AIChatNavHost(navController, AIChatAppScreens.AUTHSCREEN.route)
    }
  }
}
