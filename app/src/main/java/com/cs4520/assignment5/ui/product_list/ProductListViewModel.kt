package com.cs4520.assignment5.ui.product_list



import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment5.model.Product
import com.cs4520.assignment5.data.ProductRepository
import com.cs4520.assignment5.data.database.ProductsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductListViewModel(app: Application): AndroidViewModel(app) {

    private val productRepo : ProductRepository

    private var isLoaded = MutableLiveData<Boolean>().apply { value = false }

    private val productList = MutableLiveData<List<Product>>()

    private val errorMsg = MutableLiveData<String>()

    init {
        val dao = ProductsDatabase.getInstance(app).productDao()
        productRepo = ProductRepository(dao)
    }


    fun fetchProducts() {


        viewModelScope.launch {
            try {

               val products = withContext(Dispatchers.IO) {
                    productRepo.getAllProducts()
                }

                productList.value = products

            }
            catch(e: Exception) {

                Log.e("ProductLisViewModel", "Error getting Products" ,e)
                errorMsg.value = e.toString()
            }
            finally {
                isLoaded.value = true
            }
        }
    }

    fun fetchProductsFromWorker() {
        return
    }

    fun getProductList(): LiveData<List<Product>> {
        return productList

    }

    fun getIsLoading(): LiveData<Boolean> {
        return isLoaded
    }

    fun getErrorMessage(): LiveData<String> {
        return errorMsg
    }

}