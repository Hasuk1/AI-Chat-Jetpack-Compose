package com.example.feature.chatlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.common.OrderType
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

  ChatListScaffold(topBar = {
    IconButton(
      onClick = { viewModel.updateOrderSettingsDropdownMenuVisibility(true) },
      colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
      )
    ) {
      Icon(AppIcons.Settings, contentDescription = "chat-list-settings")
    }
    DropdownMenu(
      expanded = state.orderSettingsDropdownMenuOpen,
      onDismissRequest = { viewModel.updateOrderSettingsDropdownMenuVisibility(false) },
      offset = DpOffset(x = (-20).dp, y = 0.dp)
    ) {
      DropdownMenuItem(onClick = {
        viewModel.getAllChats(orderBy = OrderType.NEWEST)
        viewModel.updateOrderSettingsDropdownMenuVisibility(false)
      },
        text = { Text("Newest") },
        trailingIcon = { Icon(AppIcons.ArrowUp, contentDescription = "chat-order-asc") })
      DropdownMenuItem(onClick = {
        viewModel.getAllChats(orderBy = OrderType.OLDEST)
        viewModel.updateOrderSettingsDropdownMenuVisibility(false)
      },
        text = { Text("Oldest") },
        trailingIcon = { Icon(AppIcons.ArrowDown, contentDescription = "chat-order-desc") })
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
    if (state.newChatBottomSheetOpen) {
      AddNewChatBottomSheet(
        onDismissRequest = { viewModel.updateNewChatBottomSheetVisibility(false) },
        onAddNewChat = { name, description ->
          viewModel.addNewChat(name, description)
        }
      )
    }
  }, floatingButton = {
    FloatingActionButton(onClick = {
      viewModel.updateNewChatBottomSheetVisibility(true)
    }, modifier = Modifier.padding(40.dp)) {
      Icon(
        AppIcons.Add, contentDescription = "Add new chat", tint = MaterialTheme.colorScheme.primary
      )
    }
  })
}
