package br.com.fiap.aluvery.ui.theme.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.aluvery.model.Product
import br.com.fiap.aluvery.sampledata.sampleCandies
import br.com.fiap.aluvery.sampledata.sampleDrinks
import br.com.fiap.aluvery.sampledata.sampleProducts
import br.com.fiap.aluvery.sampledata.sampleSections
import br.com.fiap.aluvery.ui.theme.AluveryTheme
import br.com.fiap.aluvery.ui.theme.components.CardProductItem
import br.com.fiap.aluvery.ui.theme.components.ProductsSection
import br.com.fiap.aluvery.ui.theme.components.SearchTextField


class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchText: String = "",
    val searchedProducts: List<Product> = emptyList(),
    val onSearchChange: (String) -> Unit = {}
) {


    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }

}

@Composable
fun HomeScreen(products: List<Product>) {
    var text by remember {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) || product.description?.contains(
            text,
            ignoreCase = true,
        ) ?: false

    }

    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(
                containsInNameOrDescription()
            ) +
                    products.filter(containsInNameOrDescription())
        } else emptyList()
    }
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val searchedProducts = state.searchedProducts

        SearchTextField(
            searchText = state.searchText,
            onSearchChange = state.onSearchChange,
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp),
                    )

                }
            }


        }
//        FloatingActionButton(onClick =, shape = RoundedCornerShape(100)) {
//            Icon(imageVector = Icons.Default.Search, contentDescription = "ícone de busca")
//
//        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(sections = sampleSections))
        }
    }
}

@Preview
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUiState(
                    sections = sampleSections,
                    searchText = "a",
                )
            )
        }
    }

}