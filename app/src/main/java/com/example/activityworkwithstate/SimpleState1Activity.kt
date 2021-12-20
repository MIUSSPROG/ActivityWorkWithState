package com.example.activityworkwithstate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import com.example.activityworkwithstate.databinding.ActivityMainBinding
import com.example.activityworkwithstate.databinding.ActivitySimpleState1Binding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class SimpleState1Activity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var counterValue by notNull<Int>()
    private var counterTextColor by notNull<Int>()
    private var counterIsVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.btnInc.setOnClickListener { increment() }
        binding.btnRandColor.setOnClickListener { setRandomColor() }
        binding.btnVisibility.setOnClickListener { switchVisibility() }

        if (savedInstanceState == null){
            counterValue = 0
            counterTextColor = ContextCompat.getColor(this, R.color.purple_700)
            counterIsVisible = true
        }else{
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            counterTextColor = savedInstanceState.getInt(KEY_COLOR)
            counterIsVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, counterTextColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterIsVisible)
    }

    private fun increment(){
        counterValue++
        renderState()
    }

    private fun setRandomColor(){
        counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() = with(binding.tvCounter){
        counterIsVisible = !counterIsVisible
        renderState()
    }

    private fun renderState() = with(binding){
        tvCounter.text = counterValue.toString()
        tvCounter.setTextColor(counterTextColor)
        tvCounter.visibility = if (counterIsVisible) VISIBLE else INVISIBLE
    }

    companion object{
        private val KEY_COUNTER = "COUNTER"
        private val KEY_COLOR = "COLOR"
        private val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}