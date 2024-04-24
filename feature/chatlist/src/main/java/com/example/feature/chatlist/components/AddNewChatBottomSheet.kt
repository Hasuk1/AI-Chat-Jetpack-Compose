package com.example.feature.chatlist.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewChatBottomSheet(
  onDismissRequest: () -> Unit,
  onAddNewChat: (String, String) -> Unit,
) {
  var isChatNameEmpty by remember { mutableStateOf(false) }
  val sheetState = rememberModalBottomSheetState()
  var newChatNameText by remember { mutableStateOf("") }
  var newChatDescriptionText by remember { mutableStateOf("") }
  var isChatNameFocused by remember { mutableStateOf(false) }
  var isChaCommentFocused by remember { mutableStateOf(false) }

  val keyboardController = LocalSoftwareKeyboardController.current
  val focusRequester = remember { FocusRequester() }

  ModalBottomSheet(
    onDismissRequest = onDismissRequest,
    sheetState = sheetState,
  ) {

    LaunchedEffect(Unit) {
      focusRequester.requestFocus()
      keyboardController?.show()
    }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
      horizontalAlignment = Alignment.Start,
    ) {
      Text(
        text = "New chat",
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 15.dp)
      )
      Box(
        modifier = Modifier
          .padding(vertical = 10.dp)
          .fillMaxWidth()
          .height(60.dp),
        contentAlignment = Alignment.BottomCenter
      ) {
        OutlinedTextField(
          value = newChatNameText,
          onValueChange = { if (newChatNameText.length <= 23) newChatNameText = it },
          modifier = Modifier
            .fillMaxSize()
            .onFocusChanged { isChatNameFocused = it.isFocused }
            .focusRequester(focusRequester),
          isError = isChatNameEmpty,
          textStyle = TextStyle(fontSize = 20.sp),
          singleLine = true,
          label = { Text("Chat name", fontSize = 16.sp) },
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
          )
        )
        Box(
          modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)
            .fillMaxWidth()
            .animateContentSize()
            .background(MaterialTheme.colorScheme.primary)
            .height(if (isChatNameFocused) 3.dp else 1.dp)
        )
      }
      Box(
        modifier = Modifier
          .padding(vertical = 10.dp)
          .fillMaxWidth()
          .height(60.dp),
        contentAlignment = Alignment.BottomCenter
      ) {
        OutlinedTextField(
          value = newChatDescriptionText,
          onValueChange = { if (newChatDescriptionText.length <= 23) newChatDescriptionText = it },
          modifier = Modifier
            .fillMaxSize()
            .onFocusChanged { isChaCommentFocused = it.isFocused },
          isError = isChatNameEmpty,
          textStyle = TextStyle(fontSize = 20.sp),
          singleLine = true,
          label = { Text("Comment", fontSize = 16.sp) },
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
          )
        )
        Box(
          modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)
            .fillMaxWidth()
            .animateContentSize()
            .background(MaterialTheme.colorScheme.primary)
            .height(if (isChaCommentFocused) 3.dp else 1.dp)
        )
      }
      Button(
        onClick = {
          if (newChatNameText.isNotEmpty()) {
            onAddNewChat(newChatNameText, newChatDescriptionText)
            onDismissRequest()
          } else {
            isChatNameEmpty = true
          }
        },
        modifier = Modifier
          .padding(horizontal = 15.dp)
          .fillMaxWidth()
          .height(45.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        )
      ) {
        Text(text = "Add new chat", fontSize = 20.sp)
      }
    }
  }

}

