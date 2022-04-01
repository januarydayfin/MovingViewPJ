package com.krayapp.movingviewpj

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Path
import android.graphics.Point
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val car: View by lazy { initCar() }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()
        viewModel.mutableLiveData.observe(this) {
            checkCarInCorner()
            moveCar(it)
        }
        getWindowSize()
    }
    private fun initCar(): View = findViewById(R.id.car_view)

    private fun clickListener() {
        car.setOnClickListener {
            viewModel.calculateCar()
        }

    }

    private fun getWindowSize() {
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val height = size.y
        val width = size.x
        val arrayList = ArrayList<Int>()
        arrayList.add((width / 2) - 100) //чтобы элемент не выходил за границу - вычитаю 100 либо 200
        arrayList.add((height / 2) - 200)
        viewModel.setSize(arrayList)
    }

    /**
     * Узнать крайние координаты, и каждый раз проверять  где находится view
     */
    @SuppressLint("Recycle")
    private fun moveCar(array:IntArray) {
        val path = Path().apply {
            arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
        }
        val arcAnimation = ObjectAnimator.ofFloat(car, View.X, View.Y, path).apply {
            duration = 2000
        }
        val randomValue = Random.nextInt(-200, -100).toFloat()
        val animX = ObjectAnimator.ofFloat(car, "TranslationX", array[0].toFloat())
        val animY = ObjectAnimator.ofFloat(car, "TranslationY", array[1].toFloat())
        val animator = AnimatorSet()
        animator.playSequentially(animX, animY)
        animator.start()
    }

    private fun checkCarInCorner() {
        val carLocation = IntArray(2)
        car.getLocationOnScreen(carLocation)
        viewModel.checkCarInCorner(carLocation)
    }


}