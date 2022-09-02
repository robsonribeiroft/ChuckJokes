package com.robsonribeiroft.chuckjokes.domain.core

sealed class Error(val message: String) {

    class Default(message: String = "Something unusual happen"): Error(message)

    //domain layer
    class MissingParam(message: String = "No parameters were passed"): Error(message)
    class InvalidParam(
        message: String = ""
    ): Error("A param is invalid or missing${if (message.isEmpty()) "" else ": $message"}")

    //data layer
    class NoInternetConnection(message: String = "Device without internet connection"): Error(message)
    class Http(message: String): Error(message)
    class SocketTimeout(message: String): Error(message)
    class UnknownHost(message: String): Error(message)
}


