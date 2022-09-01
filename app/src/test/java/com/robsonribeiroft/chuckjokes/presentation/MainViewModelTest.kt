package com.robsonribeiroft.chuckjokes.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.robsonribeiroft.chuckjokes.domain.interactors.GetCategoriesUseCase
import com.robsonribeiroft.chuckjokes.domain.interactors.GetJokeUseCase
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var jokeRepository: JokeRepository

    @Mock
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Mock
    private lateinit var getJokeUseCase: GetJokeUseCase

    private val testCoroutineDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(
            getJokeUseCase = getJokeUseCase,
            getCategoriesUseCase = getCategoriesUseCase
        )
    }

}