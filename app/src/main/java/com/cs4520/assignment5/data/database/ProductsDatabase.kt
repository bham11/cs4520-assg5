package com.cs4520.assignment5.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase



@Database(entities = [Products::class], version = 2,exportSchema = false)
abstract class ProductsDatabase: RoomDatabase() {
    abstract fun productDao(): ProductsDao

    companion object{
        private var instance: ProductsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ProductsDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(context.applicationContext,
                    ProductsDatabase::class.java, "products_database",)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }
        private val roomCallback = object :Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}