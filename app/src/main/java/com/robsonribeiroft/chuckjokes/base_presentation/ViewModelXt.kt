package com.robsonribeiroft.chuckjokes.base_presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robsonribeiroft.chuckjokes.R
import com.robsonribeiroft.chuckjokes.domain.core.Error

fun <T> uiState() = lazy {
    MutableLiveData<UiState<T>>()
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this


fun ViewModel.errorHandler(error: Error?): UiText.StringResource {
    val stringRes = when(error){
        is Error.NoInternetConnection -> R.string.no_internet_connection
        is Error.SocketTimeout -> R.string.unreachable_service
        is Error.UnknownHost -> R.string.unknown_host
        is Error.Http -> R.string.http
        is Error.InvalidParam -> R.string.invalid_param
        else -> R.string.unexpected_error
    }
    return UiText.StringResource(stringRes)
}