package com.softnik.mvvm_andx_kotlin_dogsapidemo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreed::class), version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun getDogDao(): DogDao //returns DogDao Interface

    companion object {
        @Volatile private var instance: DogDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,     /*Here we are using applicationContext because regular
                                             object is volatile that can be null at certain time specially when user rotate the device*/
            DogDatabase::class.java,
            "dogdatabase"
        ).build()
    }
}