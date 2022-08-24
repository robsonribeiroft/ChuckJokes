package com.robsonribeiroft.chuckjokes.base_presentation

class UIState<T>(
    val status: Status = Status.IDLE,
    val data: T? = null,
    val error: Throwable? = null
) {

    fun stateHandler(
        loading: () -> Unit,
        onError: (Throwable) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        when (status) {
            Status.SUCCESS -> data?.let { onSuccess(it) } ?: throw RuntimeException()
            Status.ERROR -> error?.let { onError(it) } ?: throw RuntimeException()
            Status.LOADING -> loading()
            else -> Unit
        }
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, ERROR
    }
}

fun <T> UIState<T>?.isSuccess() = this?.status?.equals(UIState.Status.SUCCESS) ?: false
fun <T> UIState<T>?.isError() = this?.status?.equals(UIState.Status.ERROR) ?: false
fun <T> UIState<T>?.isLoading() = this?.status?.equals(UIState.Status.LOADING) ?: false
fun <T> UIState<T>?.isIdle() = this?.status?.equals(UIState.Status.IDLE) ?: false
