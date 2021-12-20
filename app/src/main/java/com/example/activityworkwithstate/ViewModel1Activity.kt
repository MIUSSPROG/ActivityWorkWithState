package com.example.activityworkwithstate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.activityworkwithstate.databinding.ActivityMainBinding
import com.example.activityworkwithstate.ViewModel.*
import kotlin.random.Random

class ViewModel1Activity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.btnInc.setOnClickListener { viewModel.increment() }
        binding.btnRandColor.setOnClickListener { viewModel.setRandomColor() }
        binding.btnVisibility.setOnClickListener { viewModel.switchVisibility() }
        Log.d("MY_TAG", savedInstanceState.toString())
        viewModel.initState(savedInstanceState?.getParcelable(KEY_STATE) ?: State(
                    counterValue = 0,
                    counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                    counterIsVisible = true
                )
        )

//        if (viewModel.state.value == null) {
//            viewModel.initState(
//                State(
//                    counterValue = 0,
//                    counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
//                    counterIsVisible = true
//                )
//            )
//        }

        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, viewModel.state.value)
        Log.d("MY_TAG", "recover: $outState")
    }

    private fun renderState(state: State) = with(binding) {
        tvCounter.text = state.counterValue.toString()
        tvCounter.setTextColor(state.counterTextColor)
        tvCounter.visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        val KEY_STATE = "STATE"
    }

}
