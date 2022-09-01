package com.robsonribeiroft.chuckjokes.domain.core

sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(message: String, data: T? = null): Resource<T>(data, message)
}

fun <T> Resource<T>.isSuccess() = this is Resource.Success
fun Resource<*>.isFailure() = this is Resource.Failure
