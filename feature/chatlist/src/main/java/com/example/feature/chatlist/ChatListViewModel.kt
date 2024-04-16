package com.example.feature.chatlist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hassuk1.core.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor() : ViewModel() {
  private val _state = MutableStateFlow(ChatListScreenState())
  val state = _state.asStateFlow()

  fun addNewChat() {
    val currentState = _state.value
    val newChat = Chat(
      id = currentState.chatList.size + 1,
      name = "chat ${currentState.chatList.size + 1}",
      firstPromt = "Is sample promt from chat. Is sample promt from chat. Is sample promt from chat. Is sample promt from chat. Is sample promt from chat. Is sample promt from chat. Is sample promt from chat."
    )
    _state.value = currentState.copy(chatList = _state.value.chatList + newChat)
  }
}