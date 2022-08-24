package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import com.robsonribeiroft.chuckjokes.domain.core.UseCase
import com.robsonribeiroft.chuckjokes.domain.exception.InvalidParamException
import kotlinx.coroutines.flow.Flow

class GetJokeUseCase(
    private val repository: JokeRepository
): UseCase<String, String>() {

    override fun invoke(params: String?): Flow<String> = when {
        params.isNullOrEmpty() -> throw InvalidParamException()
        else -> repository.getJokeByCategory(params)
    }
}