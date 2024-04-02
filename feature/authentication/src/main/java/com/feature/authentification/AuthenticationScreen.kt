package com.feature.authentification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.designsystem.theme.AIChatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(goTest: () -> Unit) {
  var presses by remember { mutableIntStateOf(0) }
  AIChatTheme {
    Scaffold(
      topBar = {
        TopAppBar(colors = topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
          Text("Top app bar")
        })
      },
      bottomBar = {
        BottomAppBar(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          contentColor = MaterialTheme.colorScheme.primary,
        ) {
          Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Bottom app bar",
          )
        }
      },
      /*floatingActionButton = {
           FloatingActionButton(onClick = {
             presses++
             goTest()
           }) {
             Icon(Icons.Default.Add, contentDescription = "Add")
           }
         }*/
    ) { innerPadding ->
      Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Text(
          modifier = Modifier.padding(8.dp),
          text = """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
        )
      }
    }
  }
}

@Composable
fun AuthenticationScreen(
  viewModel: AuthenticationViewModel = hiltViewModel()
) {

  AIChatTheme {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(60.dp)
          .background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Image(
          painter = rememberAsyncImagePainter(model = "https://raw.githubusercontent.com/Hasuk1/AI-Chat-Jetpack-Compose/main/misc/chat_gpt_logo.png"),
          contentDescription = "",
          modifier = Modifier.size(50.dp)
        )
      }
    }

  }
}