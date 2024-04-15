package com.hassuk1.feature.authentication.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.core.common.Result
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.feature.authentication.AuthenticationScreenState

@Composable
fun ConnectDialog(
  state: AuthenticationScreenState, toClose: () -> Unit = {}
) {
  var isDismissRequestEnable by remember { mutableStateOf(false) }
  Dialog(onDismissRequest = { if (isDismissRequestEnable) toClose() }) {
    Card(
      modifier = Modifier
        .width(100.dp)
        .height(100.dp),
      shape = RoundedCornerShape(20.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        state.connectedToApiStatus?.let { res ->
          AnimatedContent(
            res, label = ""
          ) { targetState ->
            when (targetState) {
              Result.LOADING -> {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
              }

              Result.SUCCESS -> {
                Icon(
                  imageVector = AppIcons.Check,
                  contentDescription = "IconsCheck",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(50.dp)
                )
              }

              Result.ERROR -> {
                isDismissRequestEnable = true
                Icon(
                  imageVector = AppIcons.Close,
                  contentDescription = "IconsCheck",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(50.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}