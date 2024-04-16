package com.example.feature.chatlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.feature.chatlist.components.ChatListScaffold
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.core.designsystem.icons.AppImageIcons
import com.hassuk1.core.model.Chat

@Composable
fun ChatListScreen(
  navController: NavHostController, viewModel: ChatListViewModel = hiltViewModel()
) {
  val state by viewModel.state.collectAsState()
  val lazyColumnState = rememberLazyListState()
  ChatListScaffold(topBar = {
    Icon(AppIcons.Person, contentDescription = "TEST")
  }, innerPadding = {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), state = lazyColumnState
    ) {
      items(state.chatList.size) { index ->
        ChatCard(state.chatList[index])
      }
    }
  }, floatingButton = {
    FloatingActionButton(onClick = {
      viewModel.addNewChat()
    }, modifier = Modifier.padding(40.dp)) {
      Icon(
        AppIcons.Add, contentDescription = "Add new chat", tint = MaterialTheme.colorScheme.primary
      )
    }
  })
}

@Composable
fun ChatCard(chatInfo: Chat) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(80.dp)
      .clickable { },
    verticalAlignment = Alignment.CenterVertically
  ) {
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
        .fillMaxSize()
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
        text = chatInfo.firstPromt,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground
      )
    }
  }

}