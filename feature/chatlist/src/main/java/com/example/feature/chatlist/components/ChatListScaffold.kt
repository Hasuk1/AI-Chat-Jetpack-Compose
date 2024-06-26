package com.example.feature.chatlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
          .padding(
            WindowInsets.systemBars
              .only(WindowInsetsSides.Top)
              .asPaddingValues()
          )
          .fillMaxWidth()
          .height(40.dp),
        contentAlignment = Alignment.CenterEnd
      ) {
        topBar()
      }
      Box(
        modifier = Modifier
          .fillMaxSize()
      ) {
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Top,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          innerPadding()
        }
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