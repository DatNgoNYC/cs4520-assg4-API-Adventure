package com.cs4520.assignment4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.Data.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository = ProductRepository(application.applicationContext)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    // perhaps we need some sort of status liveData here so that our view knows when to display to loading bar?
    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _noProducts = MutableLiveData<Boolean>(false)
    val noProducts = _noProducts

    // Function to load products using the repository
    fun loadProducts() {

        viewModelScope.launch {
            try {
                val results = repository.getAllProducts()
                if (results.isEmpty()) {
                    _noProducts.postValue(true)
                    _isLoading.postValue(false)
                } else {
                    _isLoading.postValue(false)
                    _products.postValue(results)
                }
            } catch (e: Error) {
                _isLoading.postValue(false)
                _noProducts.postValue(false)
            }
        }
    }
}
