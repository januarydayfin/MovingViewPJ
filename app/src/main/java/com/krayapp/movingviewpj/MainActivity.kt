package com.krayapp.movingviewpj

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val car: View by lazy { initCar() }
    private val width: Int by lazy { getWindowSize()[0] }
    private val height: Int by lazy { getWindowSize()[1] }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()
        viewModel.mutableLiveData.observe(this) {
            if (!checkCarInCorner()) {
                moveZero()
            } else {
                moveCar(it)
            }
        }
        getWindowSize()
    }

    private fun initCar(): View = findViewById(R.id.car_view)

    private fun clickListener() {
        car.setOnClickListener {
            viewModel.calculateCar()
        }
    }

    private fun getWindowSize(): IntArray {
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val height = size.y
        val width = size.x
        val intArray = IntArray(2)
        intArray[0] =
            ((width / 2) - 100) //чтобы элемент не выходил за границу - вычитаю 100 либо 200
        intArray[1] = ((height / 2) - 200)
        viewModel.setSize(intArray)
        return intArray
    }

    @SuppressLint("Recycle")
    private fun moveCar(array: IntArray) {
        val animX = ObjectAnimator.ofFloat(car, "TranslationX", array[0].toFloat())
        val animY = ObjectAnimator.ofFloat(car, "TranslationY", array[1].toFloat())
        val animator = AnimatorSet()
        animator.playSequentially(animX, animY)
        animator.start()
    }

    private fun moveZero() {
        val animX = ObjectAnimator.ofFloat(car, "TranslationX", 0.toFloat())
        val animY = ObjectAnimator.ofFloat(car, "TranslationY", 0.toFloat())
        val animator = AnimatorSet()
        animator.playSequentially(animX, animY)
        animator.start()
    }

    private fun checkCarInCorner(): Boolean {
        val carLocation = IntArray(2)
        car.getLocationOnScreen(carLocation)
        return (carLocation[0] > width || carLocation[0] < -width
                && carLocation[1] > height || carLocation[1] < -height)
    }


}