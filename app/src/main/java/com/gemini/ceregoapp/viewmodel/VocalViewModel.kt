package com.gemini.ceregoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gemini.cerego.model.Vocab

class VocalViewModel() : ViewModel() {

    val vocalRepository= VocabRepository()

    var allVocab = MutableLiveData<List<Vocab>>()

    val vocabData: LiveData<List<Vocab>>
        get() = allVocab


    fun fetchVocab() {
        allVocab = vocalRepository.getMutableLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        vocalRepository.completableJob.cancel()
    }
}