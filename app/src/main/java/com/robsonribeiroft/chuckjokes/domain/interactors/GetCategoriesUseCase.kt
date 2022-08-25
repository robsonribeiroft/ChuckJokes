package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import com.robsonribeiroft.chuckjokes.domain.core.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoriesUseCase(
    private val repository: JokeRepository
): UseCase<List<String>, Unit>() {

    override fun invoke(params: Unit?) = repository.getCategories()
}