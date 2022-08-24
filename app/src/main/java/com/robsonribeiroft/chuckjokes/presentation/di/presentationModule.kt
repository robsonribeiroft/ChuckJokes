package com.robsonribeiroft.chuckjokes.presentation.di

import com.robsonribeiroft.chuckjokes.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel(
            getJokeUseCase = get(),
            getCategoriesUseCase = get()
        )
    }

}