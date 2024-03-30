package com.cs4520.assignment5.ui.product_list



import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.cs4520.assignment5.model.Product
import com.cs4520.assignment5.data.ProductRepository
import com.cs4520.assignment5.data.database.ProductsDatabase
import com.cs4520.assignment5.data.worker.ProductListWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit


class ProductListViewModel(app: Application): AndroidViewModel(app) {

    private val productRepo : ProductRepository

    private var isLoaded = MutableLiveData<Boolean>().apply { value = false }

    private val productList = MutableLiveData<List<Product>>()

    private val errorMsg = MutableLiveData<String>()

    private val workManager = WorkManager.getInstance(app)

    init {
        val dao = ProductsDatabase.getInstance(app).productDao()
        productRepo = ProductRepository(dao, workManager)
    }


    fun fetchProducts() {


        viewModelScope.launch {
            try {

                productRepo.scheduleWork()

               val products = withContext(Dispatchers.IO) {productRepo.getAllProducts() }


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