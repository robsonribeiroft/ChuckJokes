package com.robsonribeiroft.chuckjokes.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.robsonribeiroft.chuckjokes.data.JokeRepositoryImpl
import com.robsonribeiroft.chuckjokes.data.remote.ChuckJokeWebService
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
}

@ExperimentalSerializationApi
val dataModule = module {

    single<ChuckJokeWebService> {
        Retrofit.Builder()
            .baseUrl(ChuckJokeWebService.BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(ChuckJokeWebService::class.java)
    }

    single<JokeRepository>{
        JokeRepositoryImpl(
            remote = get()
        )
    }
}