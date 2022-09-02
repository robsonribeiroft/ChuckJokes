package com.robsonribeiroft.chuckjokes.data.core

import com.robsonribeiroft.chuckjokes.domain.core.Error
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.core.Resource.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Resource<T> {
    return try {
        val response = apiCall()
        Success(response)
    } catch (e: HttpException) {
        Failure(Error.Http(e.message()))
    } catch (e: ConnectException) {
        Failure(Error.NoInternetConnection(e.toString()))
    } catch (e: SocketTimeoutException) {
        Failure(Error.SocketTimeout(e.toString()))
    } catch (e: UnknownHostException) {
        Failure(Error.UnknownHost(e.toString()))
    }
}