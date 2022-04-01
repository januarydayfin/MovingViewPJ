package com.krayapp.movingviewpj

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Path
import android.graphics.Point
import android.hardware.display.DisplayManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val car: View by lazy { initCar() }
    private val screenWidth: Int by lazy { getWindowSize()[0] }
    private val screenHeight: Int by lazy { getWindowSize()[1] }

    private val viewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()
        getWindowSize()
    }

    private fun initCar(): View = findViewById(R.id.car_view)

    private fun clickListener() {
        car.setOnClickListener {
            //viewmodel
        }

    }

    private fun getWindowSize(): ArrayList<Int> {
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val height = size.y
        val width = size.x
        val arrayList = ArrayList<Int>()
        arrayList.add((width / 2) - 100) //чтобы элемент не выходил за границу - вычитаю 100 либо 200
        arrayList.add((height / 2) - 200)

        return arrayList
    }

    /**
     * Узнать крайние координаты, и каждый раз проверять  где находится view
     */
    private fun moveCar(x: Int, y: Int) {
        val randomValue = Random.nextInt(-200, -100).toFloat()
        val animX = ObjectAnimator.ofFloat(car, "TranslationX", x.toFloat())
        val animY = ObjectAnimator.ofFloat(car, "TranslationY", y.toFloat())
        val animator = AnimatorSet()
        animator.playSequentially(animX, animY)
        animator.start()

        println("VVV Car Location ${-screenWidth}")
    }

    private fun checkCarInCorner():Boolean{
        val carLocation = IntArray(2)
        car.getLocationOnScreen(carLocation)
        if(carLocation[0]>screenWidth)
    }


}