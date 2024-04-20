package com.example.feature.chatlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.feature.chatlist.components.ChatListScaffold
import com.hassuk1.core.database.model.Chat
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.core.designsystem.icons.AppImageIcons
import kotlinx.coroutines.delay

@Composable
fun ChatListScreen(
  navController: NavHostController, viewModel: ChatListViewModel = hiltViewModel()
) {
  val state by viewModel.state.collectAsState()
  val lazyColumnState = rememberLazyListState()
//  LaunchedEffect(Unit) {
//    viewModel.getAllChats(state.userData.id)
//  }
  ChatListScaffold(topBar = {
    IconButton(
      onClick = {}, colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
      )
    ) {
      Icon(AppIcons.Settings, contentDescription = "chat-list-settings")
    }
  }, innerPadding = {


    // Отображение списка чатов
    LazyColumn(state = lazyColumnState) {
      items(state.chatList) { chat ->
        ChatCard(chat)
      }
    }
//    LazyColumn(
//      modifier = Modifier
//        .fillMaxSize()
//        .padding(vertical = 10.dp), state = lazyColumnState
//    ) {
//      items(state.chatList) { chat ->
////        val  isRemoved = remember { mutableStateOf(chat.isRemoved) }
//        Test(chat) { /*viewModel.removeChat(chat)*/ }
////        ChatCard(chat, deleteChat = { viewModel.deleteChat(chat.id) })
//      }
//    }
  }, floatingButton = {
    FloatingActionButton(onClick = {
      viewModel.addNewChat("Chat","Is sample promt from chat")
    }, modifier = Modifier.padding(40.dp)) {
      Icon(
        AppIcons.Add, contentDescription = "Add new chat", tint = MaterialTheme.colorScheme.primary
      )
    }
  })
}

@Composable
fun ChatCard(chatInfo: Chat) {
  Row(modifier = Modifier
    .clickable { }
    .padding(horizontal = 10.dp)
    .animateContentSize()
    .fillMaxWidth()
    .height(80.dp)
    .background(color = MaterialTheme.colorScheme.background),
    verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = rememberAsyncImagePainter(model = AppImageIcons.Ai),
      contentDescription = "ai_logo",
      modifier = Modifier
        .padding(5.dp)
        .background(
          color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(5.dp)
        )
        .clip(RoundedCornerShape(5.dp))
        .size(70.dp)
    )
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(1f)
        .padding(horizontal = 10.dp)
    ) {
      Text(
        text = chatInfo.name,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = chatInfo.description,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground
      )
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(chatInfo: Chat/*, isRemoved: MutableState<Boolean>*/, onRemove: () -> Unit) {
  val  isVisible = remember { mutableStateOf(true) }
  val swipeState = rememberSwipeToDismissBoxState(confirmValueChange = { value ->
    //      isRemoved.value = true
    value == SwipeToDismissBoxValue.EndToStart
  })


  AnimatedVisibility(
    visible = !isVisible.value, exit = shrinkVertically(
      animationSpec = tween(durationMillis = 500), shrinkTowards = Alignment.Top
    ) + fadeOut()
  ) {
    SwipeToDismissBox(state = swipeState, enableDismissFromStartToEnd = false, backgroundContent = {
      DeleteBackground(swipeState)
    }, modifier = Modifier.animateContentSize()) {
      ChatCard(chatInfo)
    }
  }

  LaunchedEffect(isVisible.value) {
    if (isVisible.value && swipeState.targetValue == SwipeToDismissBoxValue.EndToStart) {
      delay(500)
      onRemove()
      swipeState.snapTo(SwipeToDismissBoxValue.Settled)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
  swipeToDismissBoxState: SwipeToDismissBoxState
) {
  val color = if (swipeToDismissBoxState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
    Color.Red
  } else Color.Transparent

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color)
      .padding(16.dp),
    contentAlignment = Alignment.CenterEnd
  ) {
    Icon(
      imageVector = AppIcons.Delete, contentDescription = null, tint = Color.White
    )
  }
}