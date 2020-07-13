package com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogBreed

class DetailViewModel : ViewModel() {
    val dogListLiveData = MutableLiveData<DogBreed>()



}