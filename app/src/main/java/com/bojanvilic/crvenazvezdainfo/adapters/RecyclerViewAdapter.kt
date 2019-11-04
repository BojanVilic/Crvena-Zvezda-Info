package com.bojanvilic.crvenazvezdainfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var dataList : List<Model.Article>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.single_article_layout, parent, false)
        return ViewHolder(view)
    }

    fun readArticles(articleList : List<Model.Article>){
        dataList = articleList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}