package com.example.feature.chatlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.feature.chatlist.components.AddNewChatBottomSheet
import com.example.feature.chatlist.components.ChatCard
import com.example.feature.chatlist.components.ChatListScaffold
import com.hassuk1.core.designsystem.icons.AppIcons

@Composable
fun ChatListScreen(
  navController: NavHostController, viewModel: ChatListViewModel = hiltViewModel()
) {
  val state by viewModel.state.collectAsState()
  val lazyColumnState = rememberLazyListState()

  var showBottomSheet by remember { mutableStateOf(false) }
  val keyboardController = LocalSoftwareKeyboardController.current

  ChatListScaffold(topBar = {
    IconButton(
      onClick = {}, colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
      )
    ) {
      Icon(AppIcons.Settings, contentDescription = "chat-list-settings")
    }
  }, innerPadding = {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 5.dp), state = lazyColumnState
    ) {
      items(state.chatList) { chat ->
        ChatCard(chat) { viewModel.deleteChat(it) }
      }
    }
    if (showBottomSheet) {
      AddNewChatBottomSheet(
        onDismissRequest = { showBottomSheet = false },
        onAddNewChat = { name, description ->
          viewModel.addNewChat(name, description)
        }
      )
      keyboardController?.show()
    }
  }, floatingButton = {
    FloatingActionButton(onClick = {
      showBottomSheet = true
    }, modifier = Modifier.padding(40.dp)) {
      Icon(
        AppIcons.Add, contentDescription = "Add new chat", tint = MaterialTheme.colorScheme.primary
      )
    }
  })
}
