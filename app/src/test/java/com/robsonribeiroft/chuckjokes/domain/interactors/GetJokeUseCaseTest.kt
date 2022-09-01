package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule

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


}