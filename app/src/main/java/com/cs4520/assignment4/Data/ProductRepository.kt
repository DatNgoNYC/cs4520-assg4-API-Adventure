package com.cs4520.assignment4.Data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.Data.LocalDataSource.ProductDAO
import com.cs4520.assignment4.Data.Network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//
class ProductRepository(
    private val context: Context,
    private val dao: ProductDAO,
    private val productsApiService: RetrofitClient.ProductsApiService
) {
    suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {
        if (isOnline(context)) {
            try {
                val page_1_call =
                    productsApiService.amazonApi.getProductListByPage(1);
                val page_2_call = productsApiService.amazonApi.getProductListByPage(2)
                page_1_call
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

fun isOnline(context: Context): Boolean {
    val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}