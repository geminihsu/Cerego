package com.gemini.ceregoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gemini.cerego.model.*
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`


class VocabViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var stubRepository: VocabRepository

    @Spy // mock it partially
    @InjectMocks
    lateinit var vocabviewModel : VocalViewModel

    @Mock
    lateinit var observer: Observer<List<Vocab>>


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        stubRepository = VocabRepository()
        vocabviewModel = VocalViewModel()
        vocabviewModel.allVocab.observeForever(observer)

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
                        name = "",
                        image = Image(
                            id = 12,
                            url = "123"
                        )
                    )
                )
            )
        )


       `when`(vocabviewModel.allVocab.value).thenReturn(responseBody.response.sets)
        val newTask = stubRepository.getMutableLiveData().value?.first()

        // Then a task is saved in the repository and the view updated
        Assert.assertEquals(newTask?.name, null)

    }
}