package br.com.fiap.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.aluvery.sampledata.sampleSections
import br.com.fiap.aluvery.ui.theme.AluveryTheme
import br.com.fiap.aluvery.ui.theme.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    AluveryTheme {
        // A surface container using the 'background' color from the theme
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}