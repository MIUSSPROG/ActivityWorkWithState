package com.example.activityworkwithstate

import android.graphics.Color
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

class ViewModel : ViewModel() {

    val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    fun initState(state: State){
        stateLiveData.value = state
    }

    fun increment(){
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterValue = oldState.counterValue + 1
        )
    }

    fun setRandomColor(){
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterTextColor = Color.rgb(
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
        )
    }

    fun switchVisibility(){
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterIsVisible = !oldState.counterIsVisible)
    }

    @Parcelize
    data class State(
        val counterValue: Int,
        val counterTextColor: Int,
        val counterIsVisible: Boolean
    ): Parcelable
}