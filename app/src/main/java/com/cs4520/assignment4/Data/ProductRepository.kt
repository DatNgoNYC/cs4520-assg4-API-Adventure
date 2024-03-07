package com.cs4520.assignment4.Data

import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.Data.LocalDataSource.ProductDAO
import com.cs4520.assignment4.Data.Network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//
class ProductRepository(private val dao: ProductDAO, private val apiService: RetrofitClient.ProductsApiService) {
    suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {
        if (isOnline()) {
            try {
                val products = apiService.amazonApi
            } catch (exception: Exception) {
                // return dao.getAllProducts()
            }
        }

        return
        // if online
            // should get all the products by running the api and getting back the first two pages.
            // store in the local Room database

            // return products from local room database
        // else
            // return products from local room database
    }
}

fun isOnline(): Boolean {
    return true
}