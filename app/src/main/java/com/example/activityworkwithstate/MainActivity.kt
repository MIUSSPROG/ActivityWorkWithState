package com.example.activityworkwithstate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.activityworkwithstate.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.btnInc.setOnClickListener { increment() }
        binding.btnRandColor.setOnClickListener { setRandomColor() }
        binding.btnVisibility.setOnClickListener { switchVisibility() }
    }

    private fun increment(){
        var counter = binding.tvCounter.text.toString().toInt()
        counter++
        binding.tvCounter.text = counter.toString()
    }

    private fun setRandomColor(){
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.tvCounter.setTextColor(randomColor)
    }

    private fun switchVisibility() = with(binding.tvCounter){
        visibility = if (visibility == VISIBLE) INVISIBLE else VISIBLE
    }
}