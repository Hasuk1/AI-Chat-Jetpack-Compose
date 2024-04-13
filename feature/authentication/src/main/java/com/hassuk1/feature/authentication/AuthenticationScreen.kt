package com.hassuk1.feature.authentication

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.core.designsystem.icons.AppImageIcons
import com.hassuk1.core.model.ApiConfig
import com.hassuk1.feature.authentication.components.AuthenticationScaffold
import com.hassuk1.feature.authentication.components.InfoAlertDialog
import com.hassuk1.feature.authentication.components.InputUserKeyBar
import kotlinx.coroutines.flow.collectLatest

const val GIT_LINK = "https://github.com/Hasuk1/AI-Chat-Jetpack-Compose"

@Composable
fun AuthenticationScreen(
  viewModel: AuthenticationViewModel = hiltViewModel(), goChat: () -> Unit
) {
  val context = LocalContext.current
  val state by viewModel.state.collectAsState()
  var hideKeyboard by remember { mutableStateOf(false) }
  var isAlertDialogOpen by remember { mutableStateOf(false) }
  val activateButtonColorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
  val unActivateButtonColorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)

  LaunchedEffect(key1 = viewModel.isUserDataValid) {
    viewModel.isUserDataValid.collectLatest { isValid ->
      if (!isValid) {
        Toast.makeText(
          context, "Invalid Api Key", Toast.LENGTH_SHORT
        ).show()
      }
    }
  }

  AuthenticationScaffold(topBar = {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Image(painter = rememberAsyncImagePainter(model = AppImageIcons.Ai),
        contentDescription = "ai_logo",
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .size(35.dp)
          .clickable {
            context.startActivity(
              Intent(
                Intent.ACTION_VIEW, Uri.parse(GIT_LINK)
              )
            )
          })
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.End
    ) {
      IconButton(
        onClick = {
//          isAlertDialogOpen = true
          viewModel.test()
        }, colors = IconButtonDefaults.iconButtonColors(
          contentColor = MaterialTheme.colorScheme.primary
        )
      ) {
        Icon(AppIcons.Info, contentDescription = "Info")
      }
    }
    if (isAlertDialogOpen) {
      InfoAlertDialog(
        onDismissRequest = { isAlertDialogOpen = false },
        onConfirmation = { isAlertDialogOpen = false },
        dialogTitle = "Information",
        dialogText = "Some information.\nSome information.\nSome information.\nSome information.",
      )
    }
  }, innerPadding = {
    InputUserKeyBar(state = state,
      hint = "ApiKey",
      hideKeyboard = hideKeyboard,
      onFocusClear = { hideKeyboard = false },
      onDone = { viewModel.updateEnteredKey(it) })
    Button(
      onClick = {
        viewModel.saveUserData()
        hideKeyboard = !hideKeyboard
      },
      modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(0.95f)
        .height(50.dp),
      shape = RoundedCornerShape(20.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
      )
    ) {
      Text(text = "Connect", fontSize = 20.sp)
    }
    TextButton(onClick = {
      context.startActivity(
        Intent(
          Intent.ACTION_VIEW, Uri.parse(state.userSelectedApi.docsUsl)
        )
      )
    }) {
      Text("Read more about ${state.userSelectedApi.apiName} apikey", fontSize = 16.sp)
    }
  }, changeFocus = {
    hideKeyboard = !hideKeyboard
  }, bottomBar = {
    Box(contentAlignment = Alignment.Center,
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(0.5f)
        .clip(RoundedCornerShape(10.dp))
        .clickable {
          viewModel.updateSelectedApi(ApiConfig.NEURO)
        }) {
      Image(
        painter = rememberAsyncImagePainter(model = AppImageIcons.NeuroApi),
        modifier = Modifier.fillMaxSize(0.5f),
        contentDescription = "neuro_logo",
        colorFilter = if (state.userSelectedApi == ApiConfig.NEURO) activateButtonColorFilter else unActivateButtonColorFilter
      )
    }
    Box(contentAlignment = Alignment.Center,
      modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(10.dp))
        .clickable {
          viewModel.updateSelectedApi(ApiConfig.OPENAI)
        }) {
      Image(
        painter = rememberAsyncImagePainter(model = AppImageIcons.GptApi),
        modifier = Modifier.fillMaxSize(0.5f),
        contentDescription = "gpt_logo",
        colorFilter = if (state.userSelectedApi == ApiConfig.OPENAI) activateButtonColorFilter else unActivateButtonColorFilter
      )
    }
  })
}
