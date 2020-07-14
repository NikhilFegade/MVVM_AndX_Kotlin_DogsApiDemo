package com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application):AndroidViewModel(application) ,CoroutineScope{

    /*Here we are using AndroidViewModel not from ViewModel
    because there is difference between AndroidViewModel And ViewModel is
    the presence of applicationContext in some case weare require applicationContext
    like access database and database require regular activity context because activity context is volatile*/


    private val job  = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}