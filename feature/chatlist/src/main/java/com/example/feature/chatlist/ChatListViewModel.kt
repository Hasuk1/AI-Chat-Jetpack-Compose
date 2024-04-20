package com.example.feature.chatlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hassuk1.core.data.repository.ChatsRepository
import com.hassuk1.core.data.repository.UserDataRepository
import com.hassuk1.core.database.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatListViewModel @Inject constructor(
  private val chatsRepository: ChatsRepository,
  private val userRepository: UserDataRepository
) :
  ViewModel() {
  private val _state = MutableStateFlow(ChatListScreenState())
  val state = _state.asStateFlow()

  init {
    getUserData()
  }

  fun addNewChat(name: String, description: String) {
    viewModelScope.launch {
      val id = chatsRepository.addNewChat(_state.value.userData.id, name, description)
      Log.d("MyLog", "added new=$id")
    }
  }

  private fun getAllChats(userId: Long = _state.value.userData.id) {
    Log.d("MyLog", "getAllChats userId=$userId")
    viewModelScope.launch(Dispatchers.IO) {


    }
  }

  private fun getUserData(): Long {
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUserData().collect { userData ->
        Log.d("MyLog", "ChatListViewModel UserData=$userData")
        userData?.let {
          _state.value = _state.value.copy(
            userData = UserData(
              id = it.id,
              selectedApiUrl = it.selectedApiUrl,
              userKey = it.userKey
            )
          )
          getAllChats(userData.id)
        }
      }
    }
    return _state.value.userData.id
  }

  /*  override fun onCleared() {
      Log.d("MyLog", "before item ${_state.value.chatList}")
      val newList: MutableList<Chat> = mutableListOf()
      _state.value.chatList.forEach { chat ->
        if (!chat.isRemoved) newList.add(chat)
      }
      Log.d("MyLog", "Deleted item $newList")
      super.onCleared()
    }*/
}