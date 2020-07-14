package com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogBreed
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogDatabase
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogsApiService
import com.softnik.mvvm_andx_kotlin_dogsapidemo.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()
    private var prefHelper = SharedPreferencesHelper(getApplication())

    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L  //5min in nano-seconds

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

        val updateTime = prefHelper.getUpdateTime()
        if(updateTime!=null && updateTime!= 0L && System.nanoTime() - updateTime < refreshTime){
            fetchFromDatabase()

        }else{

            fetchFromRemote()
        }


    }

    fun refreshBypassCache(){
        fetchFromRemote()

    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val dogs = DogDatabase(getApplication()).getDogDao().getAllDogs()
            dogRetrived(dogs)
            Toast.makeText(getApplication(),"Dogs retrived from Db",Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(dogList: List<DogBreed>) {
                        storeDogListLocally(dogList)
                        Toast.makeText(getApplication(),"Dogs retrived from server",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun dogRetrived(dogList: List<DogBreed>) {
        dogsListLiveData.value = dogList
        dogsLoadError.value = false
        loading.value = false

    }

    private fun storeDogListLocally(list: List<DogBreed>) {
        launch {
            val dao = DogDatabase(getApplication()).getDogDao()
            dao.deleteAllDog()
            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }
            dogRetrived(list)
        }

        prefHelper.saveUpdateTime(System.nanoTime()) //update data retrieve time to shared preferences

    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}