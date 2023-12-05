package br.com.fiap.aluvery.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.aluvery.ui.theme.components.ProductsSection
import br.com.fiap.aluvery.ui.theme.components.sampleProducts

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(Modifier)
        ProductsSection("Promoções", sampleProducts)
        ProductsSection("Doces", sampleProducts)
        ProductsSection("Bebidas", sampleProducts)
        Spacer(Modifier)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()

}