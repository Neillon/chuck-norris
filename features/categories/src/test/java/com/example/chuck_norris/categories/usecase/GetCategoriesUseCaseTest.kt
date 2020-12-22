package com.example.chuck_norris.categories.usecase

import com.example.chuck_norris.categories.data.repository.CategoriesRepository
import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.categories.domain.usecase.GetCategoriesUseCase
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.exception.GenericNetworkException
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetCategoriesUseCaseTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `When repository returns value should return value`() =
        runBlockingTest {
            val repositoryDoc = mockk<CategoriesRepository>()
            val returnValue = Either.Value(listOf(Category("ok")))

            coEvery { repositoryDoc.getCategories() } returns returnValue

            val sut = GetCategoriesUseCase(repositoryDoc)
            val params = GetCategoriesUseCase.Params()
            val result = sut.execute(params) as Either.Value<List<Category>>

            assertEquals(returnValue, result)
        }

    @Test
    fun `When repository returns value should return error`() =
        runBlockingTest {
            val repositoryDoc = mockk<CategoriesRepository>()
            val returnValue = Either.Error(GenericNetworkException("Error testing units"))

            coEvery { repositoryDoc.getCategories() } returns returnValue

            val sut = GetCategoriesUseCase(repositoryDoc)
            val params = GetCategoriesUseCase.Params()
            val result = sut.execute(params) as Either.Error<*>

            assertEquals(returnValue, result)
        }
}