package com.robsonribeiroft.chuckjokes.domain.interactors

import com.robsonribeiroft.chuckjokes.domain.core.Resource
import com.robsonribeiroft.chuckjokes.domain.core.isSuccess
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
class GetCategoriesUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val jokeRepository = mockk<JokeRepository>()
    private lateinit var interactor: GetCategoriesUseCase

    @Before
    fun setUp() {
        interactor = GetCategoriesUseCase(jokeRepository)
    }

    @Test
    fun `when call should return a Resource Success with a list of strings`() = runTest {
        coEvery {
            jokeRepository.getCategories()
        }.coAnswers {
            Resource.Success(CATEGORIES_STUB)
        }

        val result = interactor()

        assertTrue(result.isSuccess())
        assertEquals(CATEGORIES_STUB, result.data)
    }

    companion object{
        val CATEGORIES_STUB = listOf("Cat 1","Cat 2","Cat 3","Cat 4" )
    }

}