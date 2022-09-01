package com.robsonribeiroft.chuckjokes.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robsonribeiroft.chuckjokes.base_presentation.UiState
import com.robsonribeiroft.chuckjokes.base_presentation.UiState.*
import com.robsonribeiroft.chuckjokes.domain.core.Resource

//fun <T> MutableLiveData<UiState<T>>.setSuccess(data: T) {
//    value = UiState(Status.SUCCESS, data = data)
//}
//
//fun <T> MutableLiveData<UiState<T>>.postSuccess(resource: Resource<T>) {
//    postValue(UiState(Status.SUCCESS, data = resource.data))
//}
//
//fun <T> MutableLiveData<UiState<T>>.setError(throwable: Throwable) {
//    value = UiState(Status.ERROR, error = throwable)
//}
//
//fun <T> MutableLiveData<UiState<T>>.setLoading() {
//    value = UiState(Status.LOADING)
//}
//
//fun <T> MutableLiveData<UiState<T>>.setIdle() {
//    value = UiState(Status.IDLE)
//}
//
//fun <T> LiveData<UiState<T>>.onPostValue(
//    lifecycleOwner: LifecycleOwner,
//    loading: () -> Unit,
//    onError: (Throwable) -> Unit,
//    onSuccess: (T) -> Unit
//) {
//    observe(lifecycleOwner) { uiState: UiState<T> ->
//        uiState.stateHandler(loading, onError, onSuccess)
//    }
//}