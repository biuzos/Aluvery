package br.com.fiap.aluvery.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aluvery.model.Product
import br.com.fiap.aluvery.sampledata.sampleProducts

@Composable
fun AllProductsScreen(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            Text(text = "Todos produtos", fontSize = 32.sp)
        }
        items(products) { p ->
            ProductItem(product = p)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllProductsScreenPreview() {
    AllProductsScreen(products = sampleProducts)
}