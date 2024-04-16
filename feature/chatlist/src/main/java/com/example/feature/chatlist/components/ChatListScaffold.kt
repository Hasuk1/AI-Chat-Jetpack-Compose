package com.example.feature.chatlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hassuk1.core.designsystem.theme.AIChatTheme

@Preview
@Composable
fun ChatListScaffold(
  topBar: @Composable () -> Unit = {},
  innerPadding: @Composable () -> Unit = {},
  floatingButton: @Composable () -> Unit = {}
) {
  AIChatTheme {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
    ) {
      Box(
        modifier = Modifier
          .background(color = MaterialTheme.colorScheme.primaryContainer)
          .systemBarsPadding()
          .fillMaxWidth()
          .height(40.dp),
        contentAlignment = Alignment.BottomEnd
      ) {
        topBar()
      }
      Box(modifier = Modifier.fillMaxSize()) {
        innerPadding()
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Bottom,
          horizontalAlignment = Alignment.End
        ) {
          floatingButton()
        }
      }
    }
  }
}