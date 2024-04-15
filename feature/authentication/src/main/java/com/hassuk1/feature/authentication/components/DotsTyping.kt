package com.hassuk1.feature.authentication.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun DotsTyping(dotSize: Dp = 6.dp, delayUnit: Int = 300) {
  val maxOffset = 10f

  @Composable
  fun Dot(
    offset: Float
  ) = Spacer(
    Modifier
      .size(dotSize)
      .offset(y = -offset.dp)
      .background(
        color = MaterialTheme.colorScheme.primary, shape = CircleShape
      )
  )

  val infiniteTransition = rememberInfiniteTransition(label = "")

  @Composable
  fun animateOffsetWithDelay(delay: Int) = infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 0f,
    animationSpec = infiniteRepeatable(animation = keyframes {
      durationMillis = delayUnit * 4
      0f at delay using LinearEasing
      maxOffset at delay + delayUnit using LinearEasing
      0f at delay + delayUnit * 2
    }),
    label = ""
  )

  val offset1 by animateOffsetWithDelay(0)
  val offset2 by animateOffsetWithDelay(delayUnit)
  val offset3 by animateOffsetWithDelay(delayUnit * 2)

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier.padding(top = maxOffset.dp)
  ) {
    val spaceSize = 3.dp

    Dot(offset1)
    Spacer(Modifier.width(spaceSize))
    Dot(offset2)
    Spacer(Modifier.width(spaceSize))
    Dot(offset3)
  }
}