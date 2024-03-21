package com.cs4520.assignment5.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(products: Products)

    @Query("select * from products_table")
    fun getAllProducts(): List<Products>

    @Delete
    fun delete(products: Products)

    @Query("delete from products_table")
    fun deleteAllProducts()
}