package com.hassuk1.feature.authentication

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hassuk1.core.designsystem.icons.AppIcons
import com.hassuk1.core.designsystem.icons.AppImageIcons
import com.hassuk1.core.designsystem.theme.AIChatTheme
import com.hassuk1.core.model.ApiConfig

const val GIT_LINK = "https://github.com/Hasuk1"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthenticationScreen(
  viewModel: AuthenticationViewModel = hiltViewModel(), goChat: () -> Unit
) {
  val context = LocalContext.current
  val state by viewModel.state.collectAsState()
  var inputUserKeyText by remember { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
  val focusRequester = remember { FocusRequester() }
  AIChatTheme {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(90.dp)
          .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.BottomStart
      ) {
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
        var openAlertDialog by remember { mutableStateOf(false) }
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.End
        ) {
          IconButton(
            onClick = { openAlertDialog = true }, colors = IconButtonDefaults.iconButtonColors(
              contentColor = MaterialTheme.colorScheme.primary
            )
          ) {
            Icon(AppIcons.Info, contentDescription = "Info")
          }
        }
        if (openAlertDialog) {
          InfoAlertDialog(
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = { openAlertDialog = false },
            dialogTitle = "Information",
            dialogText = "Some information.\nSome information.\nSome information.\nSome information.",
          )
        }
      }
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        BasicTextField(
          value = inputUserKeyText,
          onValueChange = { inputUserKeyText = it },
          modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(
              color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
            )
            .focusRequester(focusRequester),
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
          keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
          }),
        )
        TextButton(onClick = goChat) {
          Text("Go next screen")
        }
        TextButton(onClick = {
          viewModel.testDB()
        }) {
          Text("Test DB")
        }
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(60.dp)
          .background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        val activateButtonColorFilter =
          ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
        val unActivateButtonColorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
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
      }
    }

  }
}

@Composable
fun InfoAlertDialog(
  onDismissRequest: () -> Unit, onConfirmation: () -> Unit, dialogTitle: String, dialogText: String
) {
  AlertDialog(icon = {
    Icon(Icons.Default.Info, contentDescription = "Alert Dialog Icon")
  }, title = {
    Text(text = dialogTitle)
  }, text = {
    Text(text = dialogText)
  }, onDismissRequest = onDismissRequest, confirmButton = {
    TextButton(onClick = onConfirmation) {
      Text("Close")
    }
  })
}


@Composable
fun InputUserKeyBar(
  modifier: Modifier = Modifier,
  hint: String = "",
  hideKeyboard: Boolean = false,
  onFocusClear: () -> Unit = {},
  onDone: (String) -> Unit = {}
) {
  var text by remember {
    mutableStateOf("")
  }
  var isHintDisplayed by remember {
    mutableStateOf(false)
  }
  val focusManager = LocalFocusManager.current

  BasicTextField(value = text,
    onValueChange = {
      text = it
      onDone(it)
    },
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(onDone = {
      focusManager.clearFocus()
      onDone(text)
    }),
    singleLine = true,
    textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer),
    modifier = modifier)
  if (isHintDisplayed) {
    Text(
      text = hint,
      color = MaterialTheme.colorScheme.secondary,
      modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
    )
  }
  if (hideKeyboard) {
    focusManager.clearFocus()
    // Call onFocusClear to reset hideKeyboard state to false
    onFocusClear()
  }
}