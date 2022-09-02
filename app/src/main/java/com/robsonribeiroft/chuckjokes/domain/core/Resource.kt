package com.robsonribeiroft.chuckjokes.domain.core

sealed class Resource<T>(val data: T? = null, val error: Error? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(error: Error, data: T? = null): Resource<T>(data, error)
}

fun <T> Resource<T>.isSuccess() = this is Resource.Success
fun Resource<*>.isFailure() = this is Resource.Failure
