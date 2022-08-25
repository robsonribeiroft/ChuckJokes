package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.core.UseCase
import com.robsonribeiroft.chuckjokes.domain.exception.InvalidParamException
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import kotlinx.coroutines.flow.*

class GetJokeUseCase(
    private val repository: JokeRepository
): UseCase<String, String>() {

    override fun invoke(params: String?): Flow<String> {
        flow<Nothing> {
            if (params.isNullOrEmpty())
                throw InvalidParamException()
        }
        return repository.getJokeByCategory(params!!)
    }
}