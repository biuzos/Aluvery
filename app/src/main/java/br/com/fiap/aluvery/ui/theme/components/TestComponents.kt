package br.com.fiap.aluvery.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun myFirstComposable() {
    Text(
        text = "Hello World",
        color = Color.Black
    )
    Text(
        text = "My second Hello World",
        color = Color.Black
    )
}

@Preview(showBackground = true)
@Composable
fun ColumnPreview() {
    Column {
        Text(text = "First Text!")
        Text(text = "Second Text!")
    }
}

@Preview(showBackground = true)
@Composable
fun RowPreview() {
    Row {
        Text(text = "First Text!")
        Text(text = "Second Text!")
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLayoutPreview() {
    Column(
        Modifier
            .background(color = Color.Blue)
            .padding(24.dp)

    ) {
        Text(text = "First Text!")
        Text(text = "Second Text!")
        Row {
            Text(text = "Third Text!")
            Text(text = "Fourth Text!")
        }
        Box {
            Row {
                Text(text = "Fifth Text!")
                Text(text = "Sixth Text!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview() {
    Box {
        Text(text = "Hello World")
        Text(text = "Hello World", color = Color.Red)
    }

}

@Preview(showBackground = true)
@Composable
fun myFirstComposablePreview() {
    myFirstComposable()
}
