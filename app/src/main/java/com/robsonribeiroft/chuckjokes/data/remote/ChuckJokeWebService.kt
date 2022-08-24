package com.robsonribeiroft.chuckjokes.data.remote

import com.robsonribeiroft.chuckjokes.model.JokeApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckJokeWebService {
    companion object {
        const val BASE_URL = "https://api.chucknorris.io/jokes/"
    }

    @GET("random")
    suspend fun getJokeByCategory(@Query("category") category: String): JokeApiModel

    @GET("categories")
    suspend fun getAllCategories(): List<String>

}