package com.example.chuck_norris.categories.repository

import com.example.chuck_norris.categories.data.api.CategoriesApi
import com.example.chuck_norris.categories.data.mappers.toCategoryList
import com.example.chuck_norris.categories.data.repository.remote.CategoriesRemoteRepository
import com.example.chuck_norris.categories.domain.abstractions.CategoriesRepository
import com.example.chuck_norris.categories.mock.ClientApiMock
import com.example.chuck_norris.categories.mock.Mocks
import com.example.chuck_norris.network.Constants
import com.example.chuck_norris.network.GsonUtil
import com.example.chuck_norris.network.abstractions.Either
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class CategoriesRepositoryTest {

    private lateinit var sut: CategoriesRepository
    private lateinit var server: MockWebServer
    private lateinit var categoriesApi: CategoriesApi

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        val url = server.url("/").toString()
        categoriesApi = ClientApiMock(url).createService(CategoriesApi::class.java)

        sut = CategoriesRemoteRepository(categoriesApi)
    }

    @After
    fun tearDown() {
        server?.let { it.shutdown() }
    }

    @Test
    fun `When call getCategories, sould return list of categories`() {
        runBlocking {
            val mockedResponse = MockResponse()
            mockedResponse.setResponseCode(200)
            mockedResponse.addHeader("content-type: application/json")

            mockedResponse.setBody(GsonUtil.gsonDefault.toJson(Mocks.Categories.SERVER_RESPONSE))

            val result = sut.getCategories()
            val expectedResult = Either.value(Mocks.Categories.SERVER_RESPONSE.toCategoryList())

            when (result) {
                is Either.Value -> assertEquals(expectedResult, result.packet)
                else -> true
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When call getCategories, sould return error`() {
        runBlocking {
            val expectedResult = Either.Error(Constants.Network.Exceptions.Messages.NOT_SPECIFIED_ERROR)

            val mockedResponse = MockResponse()
            mockedResponse.setResponseCode(500)
            mockedResponse.setBody(GsonUtil.gsonDefault.toJson(expectedResult))
            server.enqueue(mockedResponse)

            val result = sut.getCategories()
            when (result) {
                is Either.Error -> assertEquals(
                    expectedResult.packet,
                    result.packet.message
                )
                else -> true
            }
        }
    }

    @Test
    fun `When call getCategories, sould return bad request error`() {
        runBlocking {
            try {
                val expectedResult = Either.Error(Constants.Network.Exceptions.Messages.BAD_REQUEST)
                val mockedResponse = MockResponse()
                mockedResponse.setResponseCode(404)
                mockedResponse.setBody(GsonUtil.gsonDefault.toJson(expectedResult))
                server.enqueue(mockedResponse)

                val result = sut.getCategories()
                when (result) {
                    is Either.Error -> assertEquals(
                        expectedResult.packet,
                        result.packet.message
                    )
                    else -> true
                }
            } catch (e: Exception) {

            }
        }
    }
}