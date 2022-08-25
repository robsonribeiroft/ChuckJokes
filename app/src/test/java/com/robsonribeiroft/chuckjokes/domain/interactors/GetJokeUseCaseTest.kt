package com.robsonribeiroft.chuckjokes.domain.interactors

import android.util.Log
import com.robsonribeiroft.chuckjokes.base_feature.emptyString
import com.robsonribeiroft.chuckjokes.domain.core.launch
import com.robsonribeiroft.chuckjokes.domain.exception.InvalidParamException
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
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetJokeUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val jokeRepository = mockk<JokeRepository>()
    private lateinit var interactor: GetJokeUseCase
    private lateinit var testRunDispatcher:  TestDispatcher

    @Before
    fun setUp() {
        interactor = GetJokeUseCase(jokeRepository)
        testRunDispatcher = UnconfinedTestDispatcher()
    }

    @After
    fun tearDown() {
        testRunDispatcher.cancel()
    }

    @Test
    fun `WHEN usecase is callled SHOULD be success`() = runTest{
        coEvery { jokeRepository.getJokeByCategory("animal") } coAnswers  { flowOf(emptyString()) }

        launch(
            useCase = interactor("animal"),
            runOnDispatcher = testRunDispatcher,
            onSuccess = { assertTrue { it.isEmpty() } }
        )
    }


    @Test
    fun `WHEN usecase is callled SHOULD throw a exception for empty string`() = runTest{
        coEvery { jokeRepository.getJokeByCategory("") } coAnswers  { flowOf(emptyString()) }
        launch(
            useCase = interactor(""),
            runOnDispatcher = testRunDispatcher,
            onError = { assertTrue( it is InvalidParamException) }
        )
    }

}