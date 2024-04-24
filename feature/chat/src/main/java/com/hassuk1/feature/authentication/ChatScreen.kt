package com.hassuk1.feature.authentication

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.hilt.navigation.compose.hiltViewModel
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.feature.authentication.components.ChatScaffold

@Composable
fun ChatScreen(
  viewModel: ChatViewModel = hiltViewModel(),
  goBack: () -> Unit
) {
  val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
  val scale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 8f,
    animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
    label = "scale"
  )
  ChatScaffold(
    topBar = {
      IconButton(
        onClick = goBack,
        colors = IconButtonDefaults.iconButtonColors(
          contentColor = MaterialTheme.colorScheme.primary
        )
      ) {
        Icon(AppIcons.ArrowBack, contentDescription = "go-back-chat-list")
      }
    },
    innerPadding = {
      Box(
        modifier = Modifier
          .fillMaxSize()
      ) {
        Text(
          text = "Hello",
          modifier = Modifier
            .graphicsLayer {
              scaleX = scale
              scaleY = scale
              transformOrigin = TransformOrigin.Center
            }
            .align(Alignment.Center),
          style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
      }
    })

}