package com.robsonribeiroft.chuckjokes.data

import com.robsonribeiroft.chuckjokes.base_feature.emptyString
import com.robsonribeiroft.chuckjokes.data.remote.ChuckJokeWebService
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.core.Resource.*
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository

class JokeRepositoryImpl(
    private val remote: ChuckJokeWebService
) : JokeRepository {
    override suspend fun getJokeByCategory(category: String): Resource<String> {
        return try {
            Success(remote.getJokeByCategory(category).joke)
        } catch (exception: Exception) {
            return Failure(exception.message ?: emptyString())
        }
    }

    override suspend fun getCategories(): Resource<List<String>>  {
        return try {
            Success(remote.getAllCategories())
        } catch (exception: Exception) {
            return Failure(exception.message ?: emptyString())
        }
    }
}