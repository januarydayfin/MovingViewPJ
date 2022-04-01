package com.krayapp.movingviewpj

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val _mutableLiveData = MutableLiveData<>()
    val mutableLiveData: LiveData<>
    get() = _mutableLiveData

    fun setSize(list:ArrayList<Int>){

    }
}