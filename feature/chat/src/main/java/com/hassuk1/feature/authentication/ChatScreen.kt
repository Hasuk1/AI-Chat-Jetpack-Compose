package com.hassuk1.feature.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.feature.authentication.components.ChatScaffold

@Composable
fun ChatScreen(
  viewModel: ChatViewModel = hiltViewModel(), goBack: () -> Unit
) {
  val state by viewModel.state.collectAsState()
  ChatScaffold(topBar = {
    Box(modifier = Modifier.fillMaxSize()) {
      IconButton(
        onClick = goBack, colors = IconButtonDefaults.iconButtonColors(
          contentColor = MaterialTheme.colorScheme.primary
        ), modifier = Modifier.align(Alignment.BottomStart)
      ) {
        Icon(AppIcons.ArrowBack, contentDescription = "go-back-chat-list")
      }
      Text(
        text = state.chatName,
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(5.dp),
        fontSize = 20.sp
      )
    }

  }, innerPadding = {

  }, bottomBar = {
    Row(
      modifier = Modifier
//        .padding(
//          WindowInsets.systemBars
//            .only(WindowInsetsSides.Bottom)
//            .asPaddingValues()
//        )
        .fillMaxSize()
    ) {
      TextField(
        value = state.userPromt,
        onValueChange = { viewModel.updateUserPromt(it) },
        modifier = Modifier
          .fillMaxHeight()
          .weight(1f)
      )
      IconButton(
        onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(
          contentColor = MaterialTheme.colorScheme.primary
        )
      ) {
        Icon(AppIcons.Send, contentDescription = "send-promt")
      }
    }
  })
}