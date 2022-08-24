package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import com.robsonribeiroft.chuckjokes.domain.core.UseCase

class GetCategoriesUseCase(
    private val repository: JokeRepository
): UseCase<List<String>, Unit>() {

    override fun invoke(params: Unit?) = repository.getCategories()
}