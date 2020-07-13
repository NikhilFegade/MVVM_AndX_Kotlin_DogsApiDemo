package com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogBreed

class ListViewModel : ViewModel() {

    val dogsListLiveData = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refresh(){
        val dog1 = DogBreed("1","Corgi","15 Years","breedGroup","","","")
        val dog2 = DogBreed("2","LabraDor","10 Years","breedGroup","","","")
        val dog3 = DogBreed("3","Rotwailer","20 Years","breedGroup","","","")

        val dogList = arrayListOf<DogBreed>(dog1 , dog2 , dog3)
        dogsListLiveData.value = dogList
        dogsLoadError.value = false
        loading.value = false

    }


}