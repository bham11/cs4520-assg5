package com.cs4520.assignment5.data



import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.cs4520.assignment5.data.database.Products
import com.cs4520.assignment5.data.database.ProductsDao
import com.cs4520.assignment5.data.database.toProduct
import com.cs4520.assignment5.data.worker.ProductListWorker
import com.cs4520.assignment5.model.Product
import com.cs4520.assignment5.model.isValidProduct
import com.cs4520.assignment5.model.toProducts
import java.util.concurrent.TimeUnit

class ProductRepository(val productDao: ProductsDao, val workManager: WorkManager) {
    private val apiService: ApiService = Api.apiService


    fun scheduleWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ProductListWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork("dataFetchWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest)

    }


    suspend fun getAllProducts(): List<Product> {

        try {

            val response = apiService.getProducts(null)
            if (response.isSuccessful) {

                val productList = this.filterValidProducts(response.body() ?: emptyList())
                if (productList.isEmpty()){
                    return emptyList()
                }
                else {
                    this.insertProductList(productList)
                    return convertToProductList(productDao.getAllProducts())
                }

            }
            else {
                throw Exception("Error Occurred: ${response.body()}")
            }

        } catch (ex: Exception) {
            Log.e("ProductRepository", "Error getting products", ex)


            val dbProducts = productDao.getAllProducts()
            return convertToProductList(dbProducts)

        }



    }

    private fun filterValidProducts(productList: List<Product>): List<Product> {
        val productListCopy = productList.toList()
        productListCopy.filter { it.isValidProduct() }
        productListCopy.toSet()
        return productListCopy.toList()
    }

    private fun insertProductList(productList: List<Product>) {
        for(product in productList) {
            productDao.insert(product.toProducts())
        }

    }

    private fun convertToProductList(dbList: List<Products>): List<Product> {

        return dbList.map{ it.toProduct() }
    }


}