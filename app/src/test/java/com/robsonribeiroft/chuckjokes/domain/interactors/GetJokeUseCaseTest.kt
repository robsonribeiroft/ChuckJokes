package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.core.isFailure
import com.robsonribeiroft.chuckjokes.domain.core.isSuccess
import com.robsonribeiroft.chuckjokes.domain.exception.InvalidParamException
import com.robsonribeiroft.chuckjokes.domain.repository.JokeRepository
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
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

    @Before
    fun setUp() {
        interactor = GetJokeUseCase(jokeRepository)
    }


    @Test
    fun `when call should return a Resource Success with a String`() = runTest {
        coEvery {
            jokeRepository.getJokeByCategory(any())
        }.coAnswers {
            Resource.Success(JOKE_STUB)
        }

        val result = interactor(GetJokeUseCase.Params(category = CATEGORY_STUB))

        assertTrue(result.isSuccess())
        assertEquals(JOKE_STUB, result.data)
    }

    @Test
    fun `when call should return a Resource Failure with a invalid param message`() = runTest {
        val message = InvalidParamException().message!!

        val result = interactor(GetJokeUseCase.Params(category = null))

        assertTrue(result.isFailure())
        assertEquals(result.message, message)
    }


    companion object{
        const val JOKE_STUB = "Joke 1"
        const val CATEGORY_STUB = "dummy category"
    }


}