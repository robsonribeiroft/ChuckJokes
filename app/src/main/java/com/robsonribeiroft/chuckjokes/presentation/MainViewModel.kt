package com.robsonribeiroft.chuckjokes.presentation

import androidx.lifecycle.*
import com.robsonribeiroft.chuckjokes.base_presentation.UIState
import com.robsonribeiroft.chuckjokes.base_presentation.asLiveData
import com.robsonribeiroft.chuckjokes.base_presentation.uiState
import com.robsonribeiroft.chuckjokes.core.setError
import com.robsonribeiroft.chuckjokes.core.setLoading
import com.robsonribeiroft.chuckjokes.core.setSuccess
import com.robsonribeiroft.chuckjokes.domain.interactors.GetCategoriesUseCase
import com.robsonribeiroft.chuckjokes.domain.interactors.GetJokeUseCase
import com.robsonribeiroft.chuckjokes.domain.core.launch

class MainViewModel(
    private val getJokeUseCase: GetJokeUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private val _categories by uiState<List<String>>()
    private val _joke by uiState<String>()

    val joke = _joke.asLiveData()
    val categories = _categories.asLiveData()

    fun getCategories() {
        _categories.setLoading()
        viewModelScope.launch(
            useCase = getCategoriesUseCase(),
            onError = _categories::setError,
            onSuccess = _categories::setSuccess
        )
    }

    fun getJoke(category: String? = null) {
        _joke.setLoading()
        viewModelScope.launch(
            useCase = getJokeUseCase(category),
            onError = _joke::setError,
            onSuccess = _joke::setSuccess
        )
    }

}