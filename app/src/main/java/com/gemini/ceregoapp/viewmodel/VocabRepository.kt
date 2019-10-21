package com.gemini.ceregoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gemini.cerego.model.Vocab
import com.gemini.ceregoapp.network.RestApiService
import com.gemini.ceregoapp.util.KEY
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import retrofit2.HttpException

class VocabRepository {

    private var movies = mutableListOf<Vocab>()
    private var mutableLiveData = MutableLiveData<List<Vocab>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        RestApiService.createCorService()
    }

    fun getMutableLiveData():MutableLiveData<List<Vocab>> {
        coroutineScope.launch {
            val request = thisApiCorService.getApiResponse(KEY)
            withContext(Dispatchers.Main) {
                try {

                    val response = request.await()
                    val mVocalWrapper = response;
                    if (mVocalWrapper != null && mVocalWrapper.response.sets != null) {
                        movies = mVocalWrapper.response.sets as MutableList<Vocab>;
                        mutableLiveData.value=movies;
                    }

                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //)
                }
            }
        }
        return mutableLiveData;
    }
}