package com.robsonribeiroft.chuckjokes.base_presentation

import com.robsonribeiroft.chuckjokes.base_presentation.UiState.Status.*
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

fun <T> MutableStateFlow<UiState<T>>.updateOnSuccess(data: T) = update {
    UiState(SUCCESS, data = data)
}

fun <T> MutableStateFlow<UiState<T>>.updateOnResource(resource: Resource<T>, defaultData: T) = update {
    when (resource) {
        is Resource.Success -> UiState(SUCCESS, data = resource.data ?: defaultData)
        is Resource.Failure -> UiState(ERROR, data = resource.data ?: defaultData, message = resource.message)
    }
}

fun <T> MutableStateFlow<UiState<T>>.updateOnError(message: String?, data: T? = null) = update {
    UiState(status = ERROR,  data = data ?: this.value.data, message = message)
}

fun <T> MutableStateFlow<UiState<T>>.updateOnLoading() = update {
    UiState(LOADING, data = this.value.data, message = this.value.message)
}

fun <T> MutableStateFlow<UiState<T>>.updateOnIdle() = update {
    UiState(IDLE, data = this.value.data, message = this.value.message)
}

fun <T> uiStateFlow(initDataState: T) = lazy {
    MutableStateFlow<UiState<T>>(UiState(IDLE, data = initDataState))
}

fun <T> MutableStateFlow<T>.asStateFlow(): StateFlow<T> = this