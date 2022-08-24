package com.robsonribeiroft.chuckjokes.domain.repository

import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    fun getJokeByCategory(category: String): Flow<String>
    fun getCategories(): Flow<List<String>>
}