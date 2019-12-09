package com.bojanvilic.crvenazvezdainfo.adapters

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import kotlinx.android.synthetic.main.horizontal_single_article_layout.view.*
import kotlinx.android.synthetic.main.single_article_layout.view.*
import kotlinx.android.synthetic.main.single_article_layout.view.article_title

class DetailRecyclerViewAdapter : RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {

    var recommendedDataList: MutableList<Model.Article> = ArrayList()
    var dataList : List<Model.Article> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_single_article_layout, parent, false)
        return ViewHolder(view)
    }

    fun readArticles(articleList: List<Model.Article>) {
        dataList = articleList
        recommendedDataList.clear()
        var i = 0
        while (i < 5) {
            recommendedDataList.add(articleList.get(i))
            i++
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recommendedDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= 24) {
            holder.title.article_title.text =
                Html.fromHtml(recommendedDataList.get(position).title.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.title.article_title.text = Html.fromHtml(recommendedDataList.get(position).title.title)
        }
        holder.title.setOnClickListener { view ->
            val bundle: Bundle = bundleOf()
            bundle.putSerializable("article", dataList.get(position))
            view.findNavController()
                .navigate(R.id.toSelf, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.article_title
    }
}