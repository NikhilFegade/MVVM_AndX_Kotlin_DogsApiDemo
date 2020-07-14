package com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogBreed
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()

    val dogsListLiveData = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refresh() {
        /* val dog1 = DogBreed("1","Corgi","15 Years","breedGroup","","","")
         val dog2 = DogBreed("2","LabraDor","10 Years","breedGroup","","","")
         val dog3 = DogBreed("3","Rotwailer","20 Years","breedGroup","","","")

         val dogList = arrayListOf<DogBreed>(dog1 , dog2 , dog3)
         dogsListLiveData.value = dogList
         dogsLoadError.value = false
         loading.value = false*/
        fetchFromRemote()

    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(dogList: List<DogBreed>) {
                        dogsListLiveData.value = dogList
                        dogsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}