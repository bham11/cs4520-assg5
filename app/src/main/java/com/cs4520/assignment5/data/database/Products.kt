package com.cs4520.assignment5.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cs4520.assignment5.model.Product


@Entity(tableName = "products_table")
data class Products(
    @PrimaryKey val name: String,
    val type: String,
    val expiryDate: String?,
    val price : Double,
)

fun Products.toProduct(): Product {
    return Product(this.name, this.type, this.expiryDate, this.price)
}
