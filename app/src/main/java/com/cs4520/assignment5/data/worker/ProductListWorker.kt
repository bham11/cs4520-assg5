package com.cs4520.assignment5.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ProductListWorker(context: Context, params: WorkerParameters, ):
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            // fetch me products
            Result.success()
        } catch (e: Throwable) {
            Log.e("ProductListWorker", e.message!!)
            Result.failure()
        }
    }
}