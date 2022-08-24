package com.robsonribeiroft.chuckjokes.data

import com.robsonribeiroft.chuckjokes.data.remote.ChuckJokeWebService
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepositoryImpl(
    private val remote: ChuckJokeWebService
) : JokeRepository {
    override fun getJokeByCategory(category: String): Flow<String> = flow {
        emit(remote.getJokeByCategory(category).joke)
    }

    override fun getCategories(): Flow<List<String>> = flow {
        emit(remote.getAllCategories())
    }
}