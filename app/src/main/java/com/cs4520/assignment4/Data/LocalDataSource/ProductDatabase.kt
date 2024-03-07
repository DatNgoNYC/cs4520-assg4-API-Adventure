package com.cs4520.assignment4.Data.LocalDataSource;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.cs4520.assignment4.Data.Entities.Product;

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    // Define an abstract method to get the DAO for the Product entity
    abstract fun productDao(): ProductDAO
}
