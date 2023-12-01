package br.com.fiap.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.aluvery.ui.theme.AluveryTheme
import br.com.fiap.aluvery.ui.theme.Pink80
import br.com.fiap.aluvery.ui.theme.Purple40
import br.com.fiap.aluvery.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    ProductItem()
                }
            }
        }
    }
}

@Composable
fun ProductItem() {
    Column(
        Modifier
            .height(250.dp)
            .width(150.dp)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Purple80,
                            Pink80
                        )
                    )
                )
                .fillMaxWidth()

        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "acessibilidade",
            Modifier.size(100.dp)
                .offset(y = (-50).dp)
                .clip(CircleShape)
                .align(alignment = CenterHorizontally)
        )
        Text(text = "Nome do produto")
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem()
}