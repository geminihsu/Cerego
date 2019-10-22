package com.gemini.ceregoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gemini.cerego.model.*

import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`


class VocabViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var vocabviewModel : VocalViewModel

    private var vocabData = MutableLiveData<List<Vocab>>()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        vocabviewModel = Mockito.mock(VocalViewModel::class.java)

        vocabData.postValue(listOf(
            Vocab(
                id = 123,
                name = "aaa",
                image = Image(
                    id = 12,
                    url = "123"
                )
            ),
            Vocab(
                id = 123,
                name = "aaa",
                image = Image(
                    id = 12,
                    url = "123"
                )
            ),
            Vocab(
                id = 123,
                name = "aaa",
                image = Image(
                    id = 12,
                    url = "123"
                )
            )
        ))
        `when`(vocabviewModel.allVocab).thenReturn(vocabData)
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
                    ),
                    Vocab(
                        id = 123,
                        name = "aaa",
                        image = Image(
                            id = 12,
                            url = "123"
                        )
                    ),
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


        val newTask = vocabviewModel.allVocab
        Assert.assertNotNull(newTask)
        Assert.assertEquals(newTask.value?.size, responseBody.response.sets.size)
        Assert.assertEquals(newTask.value, responseBody.response.sets)

    }
}