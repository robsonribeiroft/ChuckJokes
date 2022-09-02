package com.robsonribeiroft.chuckjokes.repository

import com.robsonribeiroft.chuckjokes.data.JokeRepositoryImpl
import com.robsonribeiroft.chuckjokes.data.remote.ChuckJokeWebService
import com.robsonribeiroft.chuckjokes.domain.core.Error
import com.robsonribeiroft.chuckjokes.domain.core.isFailure
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
import java.net.ConnectException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class JokeRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val webService = mockk<ChuckJokeWebService>()
    private lateinit var repository: JokeRepository

    @Before
    fun setUp() {
        repository = JokeRepositoryImpl(webService)
    }

    @Test
    fun `when call should return a Resource Success with a list of strings`() = runTest {
        coEvery {
            webService.getAllCategories()
        }.coAnswers {
            CATEGORIES_STUB
        }

        val result = repository.getCategories()

        assertTrue(result.isSuccess())
        assertEquals(CATEGORIES_STUB, result.data)
    }

    @Test
    fun `when call should return a Resource Failure with a NoInternet error`() = runTest {
        coEvery {
            webService.getAllCategories()
        }.coAnswers {
            throw ConnectException()
        }

        val result = repository.getCategories()

        assertTrue(result.isFailure())
        assertTrue(result.error is Error.NoInternetConnection)
    }

    companion object{
        val CATEGORIES_STUB = listOf("Cat 1","Cat 2","Cat 3","Cat 4" )
    }

}