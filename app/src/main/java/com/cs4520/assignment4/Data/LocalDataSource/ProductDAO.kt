package com.cs4520.assignment4.Data.LocalDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.cs4520.assignment4.Data.Entities.Product

@Dao
interface ProductDAO {
    @Query("Select * FROM products")
    fun getAllProducts(): LiveData<List<Product>>
}