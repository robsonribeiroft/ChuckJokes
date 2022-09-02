package com.robsonribeiroft.chuckjokes.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.robsonribeiroft.chuckjokes.base_presentation.isError
import com.robsonribeiroft.chuckjokes.base_presentation.isSuccess
import com.robsonribeiroft.chuckjokes.domain.core.Error
import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.interactors.GetCategoriesUseCase
import com.robsonribeiroft.chuckjokes.domain.interactors.GetJokeUseCase
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    private val getJokeUseCase = mockk<GetJokeUseCase>()
    private val getCategoriesUseCase = mockk<GetCategoriesUseCase>()

    private val coroutineContext = UnconfinedTestDispatcher()

    private lateinit var viewModel: MainViewModel


    @Before
    fun setup() {
        viewModel = MainViewModel(
            getJokeUseCase = getJokeUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
            coroutineContext = coroutineContext
        )
    }

    @After
    fun tearDown(){
        coroutineContext.cancel()
    }

    @Test
    fun `when getCategories is called should change uiState for Success`() {
        coEvery { getCategoriesUseCase() }.coAnswers { Resource.Success(CATEGORIES_STUB) }

        viewModel.getCategories()

        assertTrue(viewModel.categories.value.isSuccess())
        assertEquals(viewModel.categories.value.data.categories, CATEGORIES_STUB)
    }

    @Test
    fun `when getCategories is called should change uiState for Error`() {
        coEvery { getCategoriesUseCase() }.coAnswers { Resource.Failure(Error.Default()) }

        viewModel.getCategories()

        assertTrue(viewModel.categories.value.isError())
    }


    @Test
    fun `when getJoke is called should change uiState for Success`() {
        coEvery { getJokeUseCase(any()) }.coAnswers { Resource.Success(JOKE_STUB) }

        viewModel.getJoke()

        assertTrue(viewModel.joke.value.isSuccess())
        assertEquals(viewModel.joke.value.data.joke, JOKE_STUB)
    }

    @Test
    fun `when getJoke is called should change uiState for Error`() {
        coEvery { getJokeUseCase(any()) }.coAnswers { Resource.Failure(Error.Default()) }

        viewModel.getJoke(CATEGORY_STUB)

        assertTrue(viewModel.joke.value.isError())
    }


    companion object{
        val CATEGORIES_STUB = listOf("Cat 1","Cat 2","Cat 3","Cat 4" )
        val CATEGORY_STUB = "Cat 1"
        val JOKE_STUB = "JOKE DUMMY"
    }

}