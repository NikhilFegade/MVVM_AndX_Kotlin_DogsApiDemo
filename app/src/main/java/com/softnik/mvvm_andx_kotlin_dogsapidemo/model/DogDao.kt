package com.softnik.mvvm_andx_kotlin_dogsapidemo.model


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {

    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long> //return list o uuid

    @Query("SELECT * FROM dogbreed")
    suspend fun getAllDogs():List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE uuid =:dogId")
    suspend fun getDog(dogId:Int):DogBreed

    @Query("DELETE FROM dogbreed")
    suspend fun deleteAllDog()

}