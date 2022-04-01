package com.krayapp.movingviewpj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val car: View by lazy { initCar() }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()
    }

    private fun initCar(): View = findViewById(R.id.car_view)

    private fun clickListener() {
        car.setOnClickListener {
            Toast.makeText(this, "Car", Toast.LENGTH_SHORT).show()
        }
    }


}