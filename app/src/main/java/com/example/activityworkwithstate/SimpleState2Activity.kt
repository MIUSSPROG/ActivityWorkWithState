package com.example.activityworkwithstate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.activityworkwithstate.databinding.ActivityMainBinding
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import kotlin.random.Random

class SimpleState2Activity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d("MY_TAG", savedInstanceState.toString())
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.btnInc.setOnClickListener { increment() }
        binding.btnRandColor.setOnClickListener { setRandomColor() }
        binding.btnVisibility.setOnClickListener { switchVisibility() }

        state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
                counterValue = 0,
                counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                counterIsVisible = true)

        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
//        Log.d("MY_TAG", "recover: $outState")
    }

    private fun increment(){
        state.counterValue++
        renderState()
    }

    private fun setRandomColor(){
        state.counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() = with(binding.tvCounter){
        state.counterIsVisible = !state.counterIsVisible
        renderState()
    }

    private fun renderState() = with(binding){
        tvCounter.text = state.counterValue.toString()
        tvCounter.setTextColor(state.counterTextColor)
        tvCounter.visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    @Parcelize
    class State(
        var counterValue: Int,
        var counterTextColor: Int,
        var counterIsVisible: Boolean
    ) : Parcelable

    companion object{
        private val KEY_STATE = "STATE"
    }
}