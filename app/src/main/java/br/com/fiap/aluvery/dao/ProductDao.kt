package br.com.fiap.aluvery.dao

import br.com.fiap.aluvery.model.Product
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

class ProductDao {
    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

     fun products() = products.asStateFlow()


    fun save(product: Product) {
        products.value = products.value + product
    }
}