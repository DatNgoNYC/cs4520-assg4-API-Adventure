package com.cs4520.assignment4.Data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.Data.LocalDataSource.ProductDAO
import com.cs4520.assignment4.Data.Network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

//
class ProductRepository(
    private val dao: ProductDAO,
    private val productsApiService: RetrofitClient.ProductsApiService
) {
    suspend fun getAllProducts(): LiveData<List<Product>> = withContext(Dispatchers.IO) {
        try {
            val apiCalls = listOf(
                async { productsApiService.amazonApi.getProductListByPage(1) },
                async { productsApiService.amazonApi.getProductListByPage(2) }
            )
            val results = awaitAll(*apiCalls.toTypedArray()).mapNotNull { it.body() }.flatten()

            if (results.isNotEmpty()) {
                dao.insertAll(results)
            }

            Log.d("ApiService", "the first page: ${results.get(0)}")

            return@withContext dao.getAllProducts()

        } catch (exception: Exception) {
            println(exception.message)
        }


        return@withContext dao.getAllProducts()
    }
}
