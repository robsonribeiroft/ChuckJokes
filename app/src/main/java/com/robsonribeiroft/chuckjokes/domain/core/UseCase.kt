package com.robsonribeiroft.chuckjokes.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T, in Params> {
    abstract operator fun invoke(params: Params? = null): Flow<T>
}

fun <T> CoroutineScope.launch(
    useCase: Flow<T>,
    runOnDispatcher: CoroutineContext = Dispatchers.IO,
    onError: ((Throwable) -> Unit) = {},
    onSuccess: (T) -> Unit = {}
) {
    this.launch {
        try {
            useCase
                .flowOn(runOnDispatcher)
                .collect { onSuccess(it) }
        } catch (throwable: Throwable) {
            onError(throwable)
        }
    }
}