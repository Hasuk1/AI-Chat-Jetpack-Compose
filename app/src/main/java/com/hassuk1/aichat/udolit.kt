package com.hassuk1.aichat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PartiallySelectableText() {
  SelectionContainer {
    Column {
      Text("This text is selectable")
      Text("This one too")
      Text("This one as well")
      DisableSelection {
        Text("But not this one")
        Text("Neither this one")
      }
      Text("But again, you can select this one")
      Text("And this one too")
    }
  }
}