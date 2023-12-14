package br.com.fiap.aluvery.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.fiap.aluvery.R
import br.com.fiap.aluvery.extensions.toBrazilianCurrency
import br.com.fiap.aluvery.model.Product
import br.com.fiap.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    expanded: Boolean = false
) {
    var expandedState by rememberSaveable{
        mutableStateOf(expanded)
    }
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expandedState = !expandedState
            },

        elevation = CardDefaults.elevatedCardElevation(defaultElevation = elevation),
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }

            val textOverFlow =
                if (expandedState) TextOverflow.Visible
                else TextOverflow.Ellipsis

            val maxLines =
                if (expandedState) Int.MAX_VALUE
                else 2

            product.description?.let {
                Text(
                    text = it,
                    Modifier
                        .padding(16.dp),
                    overflow = textOverFlow,
                    maxLines = maxLines
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("100.0"),
                    description = LoremIpsum(50).values.first()
                )
            )
        }
    }
}

@Preview
@Composable
fun CardProductItemWithDescriptionExpandedPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste2",
                    price = BigDecimal("150.0"),
                    description = LoremIpsum(50).values.first()
                ),
                expanded = true
            )
        }
    }
}