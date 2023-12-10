package com.wahidabd.synrgy.presentation.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wahidabd.synrgy.utils.AuthDataStore
import com.wahidabd.synrgy.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahid on 12/10/2023.
 * Github github.com/wahidabd.
 */


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AuthViewModelTest {

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var pref: AuthDataStore

    @Mock
    private lateinit var mockObserver: Observer<Boolean>
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        viewModel = AuthViewModel(pref)
        viewModel.isLoginSuccess.observeForever(mockObserver)
    }

    @Test
    fun `test get user with valid credentials`() = runTest{
        val email = "wa@wa.id"
        val password = "wahid123"
        `when`(pref.getUser(email, password)).thenReturn(flowOf(true))

        viewModel.getUser(email, password)
        verify(mockObserver).onChanged(true)

        Assert.assertEquals(true, viewModel.isLoginSuccess.value)
    }

    @Test
    fun `test get user with invalid credentials`() = runTest {
        val email = ""
        val password = ""
        `when`(pref.getUser(email, password)).thenReturn(flowOf(false))

        viewModel.getUser(email, password)
        verify(mockObserver).onChanged(false)

        Assert.assertEquals(false, viewModel.isLoginSuccess.value)
    }
}