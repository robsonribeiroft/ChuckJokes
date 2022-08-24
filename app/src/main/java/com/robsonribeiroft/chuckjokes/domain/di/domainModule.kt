package com.robsonribeiroft.chuckjokes.domain.di

import com.robsonribeiroft.chuckjokes.domain.interactors.GetCategoriesUseCase
import com.robsonribeiroft.chuckjokes.domain.interactors.GetJokeUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetJokeUseCase(repository = get()) }
    single { GetCategoriesUseCase(repository = get()) }
}