package br.com.fiap.aluvery.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import br.com.fiap.aluvery.dao.ProductDao
import br.com.fiap.aluvery.model.Product
import br.com.fiap.aluvery.ui.theme.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {


    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<ProductFormUiState> =
        MutableStateFlow(ProductFormUiState())

    val uiState get() = _uiState

    val formater = DecimalFormat("#.##")


    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = { url ->
                    _uiState.value = _uiState.value.copy(
                        url = url,
                    )
                },
                onNameChange = { name ->
                    _uiState.value = _uiState.value.copy(
                        name = name
                    )
                },
                onPriceChange = {
                    val price = try {
                        formater.format(BigDecimal(it))
                    } catch (e: NumberFormatException) {
                        if (it.isEmpty()) {
                            it
                        } else {
                            null
                        }
                    }
                    price?.let {
                        _uiState.value = _uiState.value.copy(
                            price = price
                        )
                    }

                },
                onDescriptionChange = { description ->
                    _uiState.value = _uiState.value.copy(
                        description = description
                    )
                },
                onSaveClick = {

                }
            )

        }
    }

    fun saveProduct() {
        _uiState.value.let {
            val convertedPrice = try {
                BigDecimal(it.price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Product(
                name = it.name,
                description = it.description,
                price = convertedPrice,
                image = it.url
            )
            dao.save(product)
        }
    }


}