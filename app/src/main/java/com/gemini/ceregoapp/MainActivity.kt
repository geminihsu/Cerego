package com.gemini.ceregoapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gemini.cerego.model.Vocab
import com.gemini.ceregoapp.view.VocabPageAdapter
import com.gemini.ceregoapp.viewmodel.VocalViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel: VocalViewModel? = null
    var mVocabPageAdapter: VocabPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(VocalViewModel::class.java)
        getVocabList()
    }

    fun getVocabList() {
        mainViewModel?.fetchVocab()
        mainViewModel?.allVocab?.observe(this, Observer {  vocabList ->
            prepareRecyclerView(vocabList)
        })

    }

    private fun prepareRecyclerView(blogList: List<Vocab>?) {

        mVocabPageAdapter = VocabPageAdapter(blogList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            vocab_recycle_view.setLayoutManager(LinearLayoutManager(this))
        } else {
            vocab_recycle_view.setLayoutManager(GridLayoutManager(this, 4))
        }
        vocab_recycle_view.setItemAnimator(DefaultItemAnimator())
        vocab_recycle_view.setAdapter(mVocabPageAdapter)

    }
}

