package com.robsonribeiroft.chuckjokes.base_presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> uiState() = lazy {
    MutableLiveData<UiState<T>>()
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this