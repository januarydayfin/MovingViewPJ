package com.krayapp.movingviewpj

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _mutableLiveData = MutableLiveData<IntArray>()
    val mutableLiveData: LiveData<IntArray>
        get() = _mutableLiveData

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    fun setSize(list: IntArray) {
        screenWidth = list[0]
        screenHeight = list[1]
    }


    fun calculateCar() {
            var carMoveArray = IntArray(2)
        when(Random.nextInt(1, 4)){
            1 ->{
                carMoveArray = toTopLeft()
            }
            2 ->{
                carMoveArray = toTopRight()
            }
            3 ->{
                carMoveArray = toBotLeft()
            }
            4 ->{
                carMoveArray = toBotRight()
            }
        }
            _mutableLiveData.postValue(carMoveArray)

    }

    private fun toTopLeft():IntArray = intArrayOf(-screenWidth, screenHeight)
    private fun toTopRight():IntArray = intArrayOf(screenWidth,screenHeight)
    private fun toBotLeft():IntArray = intArrayOf(-screenWidth,-screenHeight)
    private fun toBotRight():IntArray = intArrayOf(screenWidth,-screenHeight)

}