package com.gemini.ceregoapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemini.cerego.model.Vocab
import com.gemini.ceregoapp.R

class VocabPageAdapter (val vocabList: List<Vocab>?) : RecyclerView.Adapter<VocabPageAdapter.ViewHolder>() {


    override fun getItemCount()=vocabList!!.size

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.mContext=parent.context;

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.vocab_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val vocab = vocabList!!.get(position);

        if (vocab.image.url != null) {
            Glide.with(mContext!!)
                .load(vocab.image.url)
                .into(holder.ivThumbnail);
        }
        if (vocab.name != null) {
            holder.tvTitle.setText(vocab.name);
        }

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail);
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle);

    }
}