package com.example.feature.chatlist.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.hassuk1.core.database.model.Chat
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.core.designsystem.icons.AppImageIcons

@Composable
fun ChatCard(chat: Chat, onDelete: (Chat) -> Unit) {
  val animatedAlpha by animateFloatAsState(
    targetValue = if (chat.isVisible) 1.0f else 0f,
    label = "alpha"
  )
  Row(
    modifier = Modifier
      .clickable(onClick = {
        onDelete(chat)
      })
      .padding(horizontal = 5.dp)
      .graphicsLayer {
        alpha = animatedAlpha
      }
      .animateContentSize()
      .fillMaxWidth()
      .height(80.dp)
      .background(color = MaterialTheme.colorScheme.background),
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
        .fillMaxHeight()
        .weight(1f)
        .padding(horizontal = 10.dp)
    ) {
      Text(
        text = "#${chat.id} ${chat.name}",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = chat.description,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground
      )
    }
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .width(40.dp),
      horizontalAlignment = Alignment.End,
      verticalArrangement = Arrangement.Center
    ) {
      IconButton(onClick = { /*TODO*/ },colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
      )) {
        Icon(imageVector = AppIcons.More,
          contentDescription = "more-chat-settings",
          modifier = Modifier.size(20.dp).rotate(90f))
      }
    }
  }
}


