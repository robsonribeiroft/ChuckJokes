package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.core.launch
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetCategoriesUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val jokeRepository = mockk<JokeRepository>()
    private lateinit var interactor: GetCategoriesUseCase
    private lateinit var testRunDispatcher:  TestDispatcher

    @Before
    fun setUp() {
        interactor = GetCategoriesUseCase(jokeRepository)
        testRunDispatcher = UnconfinedTestDispatcher()
    }

    @After
    fun tearDown() {
        testRunDispatcher.cancel()
    }

    @Test
    fun `WHEN usecase is callled SHOULD be success`() = runTest{
        coEvery { jokeRepository.getCategories() } coAnswers  { flowOf(emptyList()) }
        launch(
            useCase = interactor(),
            runOnDispatcher = testRunDispatcher,
            onSuccess = { assertTrue { it.isEmpty() } }
        )
    }

}