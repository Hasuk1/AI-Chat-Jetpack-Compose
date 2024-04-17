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
  private var _x = 1

  fun addNewChat() {
    val currentState = _state.value
    val newId = _x++
    val newChat = Chat(
      id = newId, name = "Chat $newId", firstPromt = "Is sample promt from chat", isRemoved = false
    )
    _state.value = currentState.copy(
      chatList = currentState.chatList + newChat
    )
    Log.d("MyLog", "Added item ${_state.value}")
  }

  fun removeChat(chat: Chat) {
    val currentState = _state.value
    _state.value = currentState.copy(
      chatList = currentState.chatList - chat
    )
    val removedChat = Chat(
      id = chat.id, name = chat.name, firstPromt = chat.firstPromt, isRemoved = true
    )
    Log.d("MyLog", "Removed item $removedChat")
    _state.value = currentState.copy(
      chatList = currentState.chatList + removedChat
    )
  }

  override fun onCleared() {
    Log.d("MyLog", "before item ${_state.value.chatList}")
    val newList:MutableList<Chat> = mutableListOf()
    _state.value.chatList.forEach{ chat ->
      if (!chat.isRemoved) newList.add(chat)
    }
    Log.d("MyLog", "Deleted item $newList")
    super.onCleared()
  }
}