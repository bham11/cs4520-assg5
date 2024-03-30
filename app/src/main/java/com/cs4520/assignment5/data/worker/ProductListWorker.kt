package com.cs4520.assignment5.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assignment5.data.ProductRepository

class ProductListWorker(context: Context, params: WorkerParameters, val productRepo: ProductRepository ):
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            productRepo.getAllProducts()
            Result.success()
        } catch (e: Throwable) {
            Log.e("ProductListWorker", e.message!!)
            Result.failure()
        }
    }
}