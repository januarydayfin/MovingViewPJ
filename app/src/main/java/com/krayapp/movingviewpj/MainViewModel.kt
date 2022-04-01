package com.krayapp.movingviewpj

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _mutableLiveData = MutableLiveData<IntArray>()
    val mutableLiveData: LiveData<IntArray>
        get() = _mutableLiveData
    private var inCorner: Boolean = false

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    fun setSize(list: ArrayList<Int>) {
        screenWidth = list[0]
        screenHeight = list[1]
    }


    fun calculateCar() {
        if (inCorner) {
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
    }

    private fun toTopLeft():IntArray = intArrayOf(-screenWidth, screenHeight)
    private fun toTopRight():IntArray = intArrayOf(screenWidth,screenHeight)
    private fun toBotLeft():IntArray = intArrayOf(-screenWidth,-screenHeight)
    private fun toBotRight():IntArray = intArrayOf(screenWidth,-screenHeight)



    fun checkCarInCorner(carLocation: IntArray) {
        inCorner = (carLocation[0] > screenWidth || carLocation[0] < -screenWidth
                && carLocation[1] > screenHeight || carLocation[1] < -screenHeight)
    }
}