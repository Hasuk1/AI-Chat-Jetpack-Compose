package com.hassuk1.feature.authentication.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.hassuk1.core.designsystem.icons.AppImageIcons
import com.hassuk1.feature.authentication.AuthenticationScreenState

@Composable
fun InputUserKeyBar(
  state: AuthenticationScreenState,
  hint: String = "",
  hideKeyboard: Boolean = false,
  onFocusClear: () -> Unit = {},
  onDone: (String) -> Unit = {}
) {
  var isHintDisplayed by remember { mutableStateOf(false) }
  var isKeyVisible by remember { mutableStateOf(false) }
  val context = LocalContext.current
  val focusManager = LocalFocusManager.current
  val clipboardManager: ClipboardManager = LocalClipboardManager.current
  Row(
    modifier = Modifier
      .padding(20.dp)
      .fillMaxWidth(0.95f)
      .height(50.dp)
      .background(
        color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
      ), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
  ) {
    Image(
      painter = rememberAsyncImagePainter(model = AppImageIcons.Key),
      modifier = Modifier
        .padding(start = 15.dp, end = 5.dp)
        .height(20.dp),
      contentDescription = "gpt_logo",
      colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
    )
    BasicTextField(value = state.userEnteredKey,
      onValueChange = {
        if (it.length <= 51) {
          onDone(it)
        }
      },
      modifier = Modifier
        .fillMaxSize()
        .weight(1f)
        .wrapContentHeight(align = Alignment.CenterVertically)
        .onFocusChanged {
          isHintDisplayed = !it.hasFocus
        },
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(onDone = {
        focusManager.clearFocus()
        onDone(state.userEnteredKey)
      }),
      visualTransformation = if (isKeyVisible) VisualTransformation.None else PasswordVisualTransformation(),
      singleLine = true,
      textStyle = TextStyle(
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontSize = 20.sp,
        textAlign = TextAlign.Start
      ),
      decorationBox = { innerTextField ->
        Row {
          if (isHintDisplayed && state.userEnteredKey.isEmpty()) {
            Text(
              text = hint,
              fontSize = 15.sp,
              color = MaterialTheme.colorScheme.secondary,
            )
          }
        }
        innerTextField()
      })
    Image(
      painter = rememberAsyncImagePainter(model = if (isKeyVisible) AppImageIcons.Visible else AppImageIcons.Invisible),
      modifier = Modifier
        .padding(start = 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .size(25.dp)
        .clickable { isKeyVisible = !isKeyVisible },
      contentDescription = "visible_pswd_logo",
      colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
    )
    Box(
      modifier = Modifier
        .fillMaxHeight(0.65f)
        .padding(start = 5.dp, end = 10.dp)
        .width(60.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color = MaterialTheme.colorScheme.secondary)
        .clickable {
          clipboardManager.getText()?.text?.let {
            onDone(it)
            Toast
              .makeText(
                context, "Pasted from your clipboard", Toast.LENGTH_SHORT
              )
              .show()
          }
        }, contentAlignment = Alignment.Center
    ) {
      Text(
        text = "Paste", color = MaterialTheme.colorScheme.onSecondary, fontWeight = FontWeight.Bold
      )
    }
  }
  if (hideKeyboard) {
    focusManager.clearFocus()
    onFocusClear()
  }
}