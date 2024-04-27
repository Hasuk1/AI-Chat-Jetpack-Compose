package com.hassuk1.feature.authentication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
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
fun ChatScaffold(
  topBar: @Composable () -> Unit = {},
  innerPadding: @Composable () -> Unit = {},
  changeFocus: () -> Unit = {},
  bottomBar: @Composable () -> Unit = {}
) {
  AIChatTheme {
    Column(
      modifier = Modifier
        .fillMaxWidth()
//        .weight(1f)
        .background(color = MaterialTheme.colorScheme.background)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null,
          onClick = changeFocus
        )
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
          .height(40.dp), contentAlignment = Alignment.BottomStart
      ) {
        topBar()
      }
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        innerPadding()
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(80.dp)
          .background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        bottomBar()
      }
//      Spacer(modifier = Modifier.height(WindowInsets.ime.asPaddingValues().calculateBottomPadding()))
    }
  }
}