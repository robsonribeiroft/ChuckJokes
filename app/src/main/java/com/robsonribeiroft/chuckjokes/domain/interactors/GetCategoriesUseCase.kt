package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.core.UseCase
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository

class GetCategoriesUseCase(
    private val repository: JokeRepository
): UseCase<List<String>, Unit>() {
    override suspend operator fun invoke(params: Unit?) = repository.getCategories()
}