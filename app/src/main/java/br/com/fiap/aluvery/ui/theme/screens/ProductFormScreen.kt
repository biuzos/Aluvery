package br.com.fiap.aluvery.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aluvery.R
import br.com.fiap.aluvery.model.Product
import br.com.fiap.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {}
) {
    var url by rememberSaveable {
        mutableStateOf("")
    }

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var price by rememberSaveable {
        mutableStateOf("")
    }
    val formater = remember {
        DecimalFormat("#.##")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(
            text = "Criando o produto",
            Modifier
                .fillMaxWidth(),
            fontSize = 28.sp
        )

        if (url.isNotBlank()) {
            AsyncImage(
                model = url, contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
        }

        TextField(
            value = url, onValueChange = {
                url = it
            }, Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "URL da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = name, onValueChange = {
                name = it
            }, Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        TextField(
            value = price, onValueChange = {
                try {
                    price = formater.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        price = it
                    }
                }
            }, Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = description, onValueChange = {
                description = it
            }, Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            placeholder = {
                Text(text = "Descrição do produto")
            }
        )
        Button(
            onClick = {
                val convertedPrice = try {
                    BigDecimal(price)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    image = url,
                    price = convertedPrice,
                    description = description
                )
                Log.i("ProductForm", "Product: $product")
                onSaveClick(product)

            }, Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
        Spacer(modifier = Modifier)
    }
}

@Preview()
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}