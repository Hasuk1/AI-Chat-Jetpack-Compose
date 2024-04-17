package com.hassuk1.aichat.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.chatlist.ChatListScreen
import com.hassuk1.feature.authentication.AuthenticationScreen

@Composable
fun AIChatNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AIChatAppScreens.AUTHSCREEN.route) {
      AuthenticationScreen(goChatList = {
        navController.navigate(AIChatAppScreens.CHATLISTCREEN.route) {
          popUpTo(AIChatAppScreens.AUTHSCREEN.route) {
//            inclusive = true
          }
        }
      })

    }
//    composable(AIChatAppScreens.CHATCREEN.route, enterTransition = {
//      fadeIn(
//        animationSpec = tween(
//          300, easing = LinearEasing
//        )
//      ) + slideIntoContainer(
//        animationSpec = tween(300, easing = EaseIn),
//        towards = AnimatedContentTransitionScope.SlideDirection.Start
//      )
//    }, exitTransition = {
//      fadeOut(
//        animationSpec = tween(
//          300, easing = LinearEasing
//        )
//      ) + slideOutOfContainer(
//        animationSpec = tween(300, easing = EaseOut),
//        towards = AnimatedContentTransitionScope.SlideDirection.End
//      )
//    }) {
//      ChatScreen {
//        navController.navigate(AIChatAppScreens.CHATLISTCREEN.route)
//      }
//    }

    composable(AIChatAppScreens.CHATLISTCREEN.route, enterTransition = {
      fadeIn(
        animationSpec = tween(
          300, easing = LinearEasing
        )
      ) + slideIntoContainer(
        animationSpec = tween(300, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
      )
    }, exitTransition = {
      fadeOut(
        animationSpec = tween(
          300, easing = LinearEasing
        )
      ) + slideOutOfContainer(
        animationSpec = tween(300, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
      )
    }) {
      ChatListScreen(navController = navController)
    }
  }
}