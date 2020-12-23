package com.example.chuck_norris.categories.abstractions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.chuck_norris.categories.rules.TestCoroutineRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseCoroutineTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

}