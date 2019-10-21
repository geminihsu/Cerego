package com.gemini.ceregoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gemini.cerego.model.Vocab

class VocalViewModel() : ViewModel() {

    val vocalRepository= VocabRepository()
    val allVocab: LiveData<List<Vocab>> get() = vocalRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        vocalRepository.completableJob.cancel()
    }
}