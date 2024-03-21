package com.cs4520.assignment5.model

import com.cs4520.assignment5.data.database.Products
import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("expiryDate")
    val expiryDate: String?,
    @SerializedName("price")
    val price : Double,

)

fun Product.toProducts(): Products {
    return Products(this.name,this.type,this.expiryDate, this.price)
}

fun Product.isValidProduct(): Boolean {
    return name.isNotEmpty() && type.isNotEmpty() && price.toString().isNotEmpty()
}






