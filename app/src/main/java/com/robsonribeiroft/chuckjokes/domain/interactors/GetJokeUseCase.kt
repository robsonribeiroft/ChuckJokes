package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.base_feature.emptyString
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.core.Resource.Failure
import com.robsonribeiroft.chuckjokes.domain.core.UseCase
import com.robsonribeiroft.chuckjokes.domain.exception.InvalidParamException
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository

class GetJokeUseCase(
    private val repository: JokeRepository
): UseCase<String, GetJokeUseCase.Params>() {

    override suspend operator fun invoke(params: Params?): Resource<String> {
        return when{
            params == null -> Failure(InvalidParamException().message ?: emptyString())
            params.category.isNullOrEmpty() -> Failure(InvalidParamException().message ?: emptyString())
            else -> repository.getJokeByCategory(params.category)
        }
    }

    data class Params(
        val category: String? = null
    )
}