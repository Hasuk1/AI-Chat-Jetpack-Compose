/*
 * MIT License
 *
 * Copyright (c) "2024" Mikhail Karpushov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hassuk1.feature.authentication

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.Resource
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.model.Message
import com.hassuk1.core.data.repository.ChatRepository
import com.hassuk1.core.data.repository.ChatsRepository
import com.hassuk1.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val userRepository: UserDataRepository,
  private val chatsRepository: ChatsRepository,
  private val chatRepository: ChatRepository
) : ViewModel() {
  private val _state = MutableStateFlow(ChatScreenState())
  val state = _state.asStateFlow()

  init {
    getChatData()
  }

  fun updateUserPromt(newPromt:String){
    _state.value = _state.value.copy(userPromt = newPromt)
  }

  fun sendPromt(promt:String) {
    viewModelScope.launch {
      chatRepository.getCompletionToPromt(
        requestDTO = ChatCompletionRequestDTO(
          messages = listOf(
            Message(
              content = promt, role = "user"
            )
          )
        ), userKey = _state.value.userApiKey
      ).collectLatest { resource ->
        when (resource) {
          is Resource.Success -> {
            val test = resource.data?.choices?.first()?.message?.content ?: "error answer"
            Log.d("MyLog", "Answer->$test")
          }

          is Resource.Error -> {
            Log.d("MyLog", "Error->${resource.message}")
          }

          is Resource.Loading -> {
            Log.d("MyLog", "Loading answer")
          }
        }
      }
    }
  }

  private fun getChatData() {
    getChatId()
    getUserKey()
    getChatName()
    getChatName()
  }

  private fun getChatId() {
    viewModelScope.launch {
      savedStateHandle.get<String>("chatId")?.let { chatId ->
        _state.value = _state.value.copy(chatId = chatId.toLong())
      }
    }
  }

  private fun getUserKey() {
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUserData().collectLatest { userData ->
        userData?.let {
          _state.value = _state.value.copy(userApiKey = it.userKey)
        }
      }
    }
  }

  private fun getChatName() {
    viewModelScope.launch(Dispatchers.IO) {
      chatsRepository.getChatDataById(_state.value.chatId).collectLatest { chatData ->
        if (chatData != null) {
          _state.value = _state.value.copy(chatName = chatData.name)
        } else {
          _state.value = _state.value.copy(chatName = "Chat with GPT")
        }
      }
    }
  }

  private fun getChatMessages(){
    viewModelScope.launch(Dispatchers.IO) {

    }
  }
}