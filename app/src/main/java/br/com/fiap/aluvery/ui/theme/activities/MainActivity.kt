package br.com.fiap.aluvery.ui.theme.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.aluvery.dao.ProductDao

import br.com.fiap.aluvery.sampledata.sampleSections
import br.com.fiap.aluvery.ui.theme.AluveryTheme
import br.com.fiap.aluvery.ui.theme.screens.HomeScreen
import br.com.fiap.aluvery.ui.theme.states.HomeScreenUiState
import br.com.fiap.aluvery.ui.theme.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(
                    Intent(
                        this,
                        ProductFormActivity::class.java
                    )
                )
            }) {
                val viewModel by viewModels<HomeScreenViewModel>()
                HomeScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        // A surface container using the 'background' color from the theme
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

