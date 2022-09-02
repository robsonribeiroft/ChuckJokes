package com.robsonribeiroft.chuckjokes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robsonribeiroft.chuckjokes.base_feature.emptyString
import com.robsonribeiroft.chuckjokes.base_presentation.*
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.interactors.GetCategoriesUseCase
import com.robsonribeiroft.chuckjokes.domain.interactors.GetJokeUseCase
import com.robsonribeiroft.chuckjokes.feature.model.CategoriesBindingModel
import com.robsonribeiroft.chuckjokes.feature.model.JokeBindingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val getJokeUseCase: GetJokeUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
): ViewModel() {

    private val _categories by uiStateFlow(CategoriesBindingModel())
    private val _joke by uiStateFlow(JokeBindingModel())

    val joke = _joke.asStateFlow()
    val categories = _categories.asStateFlow()

    fun getCategories() {
        _categories.updateOnLoading()
        viewModelScope.launch(coroutineContext) {
            when(val result: Resource<List<String>> = getCategoriesUseCase()){
                is Resource.Success -> _categories.updateOnSuccess(toCategoriesBindingModelMapper(result))
                is Resource.Failure -> {
                    Log.d("getCategoriesFailure", result.error.toString())
                    _categories.updateOnError(errorHandler(result.error))
                }
            }
        }
    }

    fun getJoke(category: String? = null) {
        _joke.updateOnLoading()
        viewModelScope.launch(coroutineContext) {
            when(val result: Resource<String> = getJokeUseCase(GetJokeUseCase.Params(category=category))){
                is Resource.Success -> _joke.updateOnSuccess(toJokeBindingModelMapper(result))
                is Resource.Failure -> {
                    Log.d("getJokeFailure", result.error.toString())
                    _joke.updateOnError(errorHandler(result.error))
                }
            }
        }
    }

    private fun toJokeBindingModelMapper(resource: Resource<String>): JokeBindingModel {
        return JokeBindingModel(resource.data ?: emptyString())
    }

    private fun toCategoriesBindingModelMapper(resource: Resource<List<String>>): CategoriesBindingModel {
        return CategoriesBindingModel(resource.data ?: emptyList())
    }

}