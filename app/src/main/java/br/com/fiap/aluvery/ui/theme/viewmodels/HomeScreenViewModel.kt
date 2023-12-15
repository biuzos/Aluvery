package br.com.fiap.aluvery.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aluvery.dao.ProductDao
import br.com.fiap.aluvery.model.Product
import br.com.fiap.aluvery.sampledata.sampleCandies
import br.com.fiap.aluvery.sampledata.sampleDrinks
import br.com.fiap.aluvery.sampledata.sampleProducts
import br.com.fiap.aluvery.ui.theme.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()


    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(
        HomeScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { text ->
                    _uiState.value = _uiState.value.copy(
                        searchText = text,
                        searchedProducts = searchedProducts(text)
                    )
                }
            )

        }
        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }

    }

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) || product.description?.contains(
            text,
            ignoreCase = true,
        ) ?: false

    }

    private fun searchedProducts(text: String): List<Product> = if (text.isNotBlank()) {
        sampleProducts.filter(
            containsInNameOrDescription(text)
        ) +
                dao.products().value.filter(containsInNameOrDescription(text))
    } else emptyList()

}
