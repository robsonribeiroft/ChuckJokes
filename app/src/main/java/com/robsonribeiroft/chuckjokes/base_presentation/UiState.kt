package com.robsonribeiroft.chuckjokes.base_presentation

data class UiState<T>(
    val status: Status = Status.IDLE,
    val data: T,
    val message: UiText? = null
) {

    fun stateHandler(
        loading: () -> Unit,
        onError: (message: UiText, data: T) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        when (status) {
            Status.SUCCESS -> onSuccess(data)
            Status.ERROR -> message?.let { onError(it, data) } ?: throw RuntimeException()
            Status.LOADING -> loading()
            else -> Unit
        }
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, ERROR
    }
}

fun <T> UiState<T>?.isSuccess() = this?.status?.equals(UiState.Status.SUCCESS) ?: false
fun <T> UiState<T>?.isError() = this?.status?.equals(UiState.Status.ERROR) ?: false
fun <T> UiState<T>?.isLoading() = this?.status?.equals(UiState.Status.LOADING) ?: false
fun <T> UiState<T>?.isIdle() = this?.status?.equals(UiState.Status.IDLE) ?: false
