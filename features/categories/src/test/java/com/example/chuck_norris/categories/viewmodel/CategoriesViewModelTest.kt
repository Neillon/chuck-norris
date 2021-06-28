package com.example.chuck_norris.categories.viewmodel

import androidx.lifecycle.Observer
import com.example.chuck_norris.categories.abstractions.BaseCoroutineTest
import com.example.chuck_norris.categories.data.mappers.toUI
import com.example.chuck_norris.categories.domain.usecase.GetCategoriesUseCaseImpl
import com.example.chuck_norris.categories.mock.Mocks
import com.example.chuck_norris.categories.presentation.categories.CategoriesViewModel
import com.example.chuck_norris.categories.presentation.categories.data.CategoriesViewEvent
import com.example.chuck_norris.categories.presentation.categories.data.CategoriesViewState
import com.example.chuck_norris.network.abstractions.Either
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoriesViewModelTest : BaseCoroutineTest() {

    private lateinit var viewModel: CategoriesViewModel

    @MockK(relaxed = true)
    private lateinit var getCategoriesUseCase: GetCategoriesUseCaseImpl

    @MockK(relaxed = true)
    private lateinit var viewStateObserver: Observer<CategoriesViewState>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CategoriesViewModel(getCategoriesUseCase)
    }

    @Test
    fun WhenLoadCategoriesEventshouldupdateViewStatetoLoading() =
        testCoroutineRule.runBlockingTest {

            coEvery {
                getCategoriesUseCase.execute(any())
            } returns Either.Value(Mocks.Categories.ALL)

            val mockedResult = mutableListOf<CategoriesViewState>()
            val mockObserver = spyk(Observer<CategoriesViewState> {
                mockedResult.add(it)
            })

            viewModel.viewState.observeForever(mockObserver)
            viewModel.processEvent(CategoriesViewEvent.LoadCategories)

            verify(exactly = 3) {
                mockObserver.onChanged(any())
            }

            assertEquals(CategoriesViewState(isLoading = false), mockedResult[0])
            assertEquals(CategoriesViewState(isLoading = true), mockedResult[1])
            assertEquals(
                CategoriesViewState(isLoading = false, categories = Mocks.Categories.ALL.toUI()),
                mockedResult[2]
            )
        }

    @Test
    fun `When RefreshCategoriesEvent, should update ViewState to Loading`() =
        testCoroutineRule.runBlockingTest {

            coEvery {
                getCategoriesUseCase.execute(any())
            } returns Either.Value(Mocks.Categories.ALL)

            val mockedResult = mutableListOf<CategoriesViewState>()
            val mockObserver = spyk(Observer<CategoriesViewState> {
                mockedResult.add(it)
            })

            viewModel.viewState.observeForever(mockObserver)
            viewModel.processEvent(CategoriesViewEvent.RefreshCategories)

            verify(exactly = 3) {
                mockObserver.onChanged(any())
            }

            assertEquals(CategoriesViewState(isLoading = false), mockedResult[0])
            assertEquals(CategoriesViewState(isLoading = true), mockedResult[1])
            assertEquals(
                CategoriesViewState(isLoading = false, categories = Mocks.Categories.ALL.toUI()),
                mockedResult[2]
            )
        }

}