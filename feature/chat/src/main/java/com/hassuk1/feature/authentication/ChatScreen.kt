package com.hassuk1.feature.authentication

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.hilt.navigation.compose.hiltViewModel

const val GIT_LINK = "https://github.com/Hasuk1"

@Composable
fun ChatScreen(
  viewModel: ChatViewModel = hiltViewModel(),
  goChat:() -> Unit
) {
  val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
  val scale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 8f,
    animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
    label = "scale"
  )
  Box(modifier = Modifier.fillMaxSize().clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = goChat
  )) {
    Text(
      text = "Hello",
      modifier = Modifier
        .graphicsLayer {
          scaleX = scale
          scaleY = scale
          transformOrigin = TransformOrigin.Center
        }
        .align(Alignment.Center),
      // Text composable does not take TextMotion as a parameter.
      // Provide it via style argument but make sure that we are copying from current theme
      style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
    )
  }
}