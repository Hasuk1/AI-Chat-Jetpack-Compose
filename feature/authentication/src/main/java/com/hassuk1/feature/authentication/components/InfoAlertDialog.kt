package com.hassuk1.feature.authentication.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

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