package com.example.feature.chatlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hassuk1.core.data.repository.ChatsRepository
import com.hassuk1.core.data.repository.UserDataRepository
import com.hassuk1.core.database.model.Chat
import com.hassuk1.core.database.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatListViewModel @Inject constructor(
  private val chatsRepository: ChatsRepository, private val userRepository: UserDataRepository
) : ViewModel() {
  private val _state = MutableStateFlow(ChatListScreenState())
  val state = _state.asStateFlow()

  init {
    getUserData()
  }

  fun addNewChat(name: String, description: String) {
    viewModelScope.launch {
      val newChat = Chat(
        userId = _state.value.userData.id, name = name, description = description, isVisible = true
      )
      val id = chatsRepository.addNewChat(newChat)
      Log.d("MyLog", "added chatId=$id newChat=$newChat")
    }
  }

  fun deleteChat(chat: Chat) {
    val markedChat = Chat(
      id = chat.id,
      userId = chat.userId,
      name = chat.name,
      description = chat.description,
      isVisible = false
    )
    viewModelScope.launch {
      chatsRepository.updateChatData(markedChat)
      delay(800)
      chatsRepository.deleteChat(chat)
    }
  }

  fun updateNewChatBottomSheetVisibility(isOpen: Boolean) {
    viewModelScope.launch {
      _state.value = _state.value.copy(newChatBottomSheetOpen = isOpen)
    }
  }

  private fun getAllChats(userId: Long = _state.value.userData.id) {
    Log.d("MyLog", "getAllChats userId=$userId")
    viewModelScope.launch(Dispatchers.IO) {
      chatsRepository.getAllChats(userId).collectLatest { chats ->
        _state.value = _state.value.copy(chatList = chats)
      }
    }
  }

  private fun getUserData() {
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUserData().collect { userData ->
        Log.d("MyLog", "ChatListViewModel UserData=$userData")
        userData?.let {
          _state.value = _state.value.copy(
            userData = UserData(
              id = it.id, selectedApiUrl = it.selectedApiUrl, userKey = it.userKey
            )
          )
          getAllChats(userData.id)
        }
      }
    }
  }
}