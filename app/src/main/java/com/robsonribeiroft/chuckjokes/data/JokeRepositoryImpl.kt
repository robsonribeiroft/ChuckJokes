package com.robsonribeiroft.chuckjokes.data

import com.robsonribeiroft.chuckjokes.data.core.safeApiCall
import com.robsonribeiroft.chuckjokes.data.remote.ChuckJokeWebService
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository

class JokeRepositoryImpl(
    private val remote: ChuckJokeWebService
) : JokeRepository {
    override suspend fun getJokeByCategory(category: String): Resource<String> {
        return safeApiCall {
            remote.getJokeByCategory(category).joke
        }
    }

    override suspend fun getCategories(): Resource<List<String>>  {
        return safeApiCall {
            remote.getAllCategories()
        }
    }
}