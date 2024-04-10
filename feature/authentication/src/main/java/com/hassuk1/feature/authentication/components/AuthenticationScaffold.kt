package com.hassuk1.feature.authentication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.hassuk1.core.designsystem.theme.AIChatTheme

@Composable
fun AuthenticationScaffold(
  topBar: @Composable () -> Unit = {},
  innerPadding: @Composable () -> Unit = {},
  changeFocus: () -> Unit = {},
  bottomBar: @Composable () -> Unit = {}
) {
  AIChatTheme {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null,
          onClick = changeFocus
        )
    ) {

    }
  }
}

