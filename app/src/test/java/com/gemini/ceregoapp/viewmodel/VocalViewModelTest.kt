package com.gemini.ceregoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gemini.cerego.model.*
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class VocalViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var stubRepository: VocabRepository

    @Mock
    lateinit var viewModel : VocalViewModel

    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getSet() {
        val responseBody = EntityResponse(
            meta = Meta(
                status = 200,
                message = "OK"
            ),
            response = Response(
                sets_count = 3,
                sets = listOf(
                    Vocab(
                        id = 123,
                        name = "",
                        image = Image(
                            id = 12,
                            url = "123"
                        )
                    )
                )
            )
        )

        Mockito.`when`(stubRepository.getMutableLiveData().value).thenReturn(responseBody.response.sets)


       // Assert.assertNotNull("io", stubRepository.getMutableLiveData().value)

    }
}