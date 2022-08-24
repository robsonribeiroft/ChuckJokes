package com.robsonribeiroft.chuckjokes.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robsonribeiroft.chuckjokes.base_presentation.UIState
import com.robsonribeiroft.chuckjokes.base_presentation.UIState.*

fun <T> MutableLiveData<UIState<T>>.setSuccess(data: T) {
    value = UIState(Status.SUCCESS, data = data)
}

fun <T> MutableLiveData<UIState<T>>.setError(throwable: Throwable) {
    value = UIState(Status.ERROR, error = throwable)
}

fun <T> MutableLiveData<UIState<T>>.setLoading() {
    value = UIState(Status.LOADING)
}

fun <T> MutableLiveData<UIState<T>>.setIdle() {
    value = UIState(Status.IDLE)
}

fun <T> LiveData<UIState<T>>.onPostValue(
    lifecycleOwner: LifecycleOwner,
    loading: () -> Unit,
    onError: (Throwable) -> Unit,
    onSuccess: (T) -> Unit
) {
    observe(lifecycleOwner) { uiState: UIState<T> ->
        uiState.stateHandler(loading, onError, onSuccess)
    }
}