package com.gemini.ceregoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.gemini.cerego.model.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class VocabRepositoryTest
{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var vocabRepository: VocabRepository

    private var vocabData = MutableLiveData<List<Vocab>>()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        vocabData.postValue(listOf(
            Vocab(
                id = 123,
                name = "aaa",
                image = Image(
                    id = 12,
                    url = "123"
                )
            )
        ))
        `when`(vocabRepository.getMutableLiveData()).thenReturn(vocabData)
    }

    @Test
    fun saveVocalRepository_showsSuccess() {
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
                        name = "aaa",
                        image = Image(
                            id = 12,
                            url = "123"
                        )
                    )
                )
            )
        )


        val data = vocabRepository.getMutableLiveData()
        Assert.assertEquals(data.value, responseBody.response.sets)
    }

}