package com.cs4520.assignment4.Data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
    private val context: Context,
    private val dao: ProductDAO,
    private val productsApiService: RetrofitClient.ProductsApiService
) {
    suspend fun getAllProducts(): LiveData<List<Product>> = withContext(Dispatchers.IO) {
        if (isOnline(context)) {
            try {
                val apiCalls = listOf(
                    async { productsApiService.amazonApi.getProductListByPage(1) },
                    async { productsApiService.amazonApi.getProductListByPage(2) }
                )
                val results = awaitAll(*apiCalls.toTypedArray()).mapNotNull { it.body() }.flatten()

                if (results.isNotEmpty()) {
                    dao.insertAll(results)
                }

            } catch (exception: Exception) {
                println(exception.message)
            }
        } else {
            return@withContext dao.getAllProducts()
        }

        return@withContext dao.getAllProducts()
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

}