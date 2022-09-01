package com.robsonribeiroft.chuckjokes.domain.repository

import com.robsonribeiroft.chuckjokes.domain.core.Resource

interface JokeRepository {
    suspend fun getJokeByCategory(category: String): Resource<String>
    suspend fun getCategories(): Resource<List<String>>
}