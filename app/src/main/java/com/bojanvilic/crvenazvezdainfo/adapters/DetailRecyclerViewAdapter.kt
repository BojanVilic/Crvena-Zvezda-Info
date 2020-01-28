package com.bojanvilic.crvenazvezdainfo.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import kotlinx.android.synthetic.main.single_article_layout.view.article_title

class DetailRecyclerViewAdapter : RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {

    var recommendedDataList: MutableList<ArticleModelRoom> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_single_article_layout, parent, false)
        return ViewHolder(view)
    }

    fun readArticles(articleList: List<ArticleModelRoom>, id: Int) {
        recommendedDataList.clear()
        var i = 0
        var articleCount = 5
        while (i < articleCount) {
            if (id != articleList[i].id) {
                recommendedDataList.add(articleList[i])
                i++
            } else {
                i++
                articleCount++
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recommendedDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.article_title.text = HtmlCompat.fromHtml(recommendedDataList[position].title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        holder.title.setOnClickListener { view ->
            val bundle: Bundle = bundleOf()
            bundle.putSerializable("article", recommendedDataList[position])
            view.findNavController()
                .navigate(R.id.toSelf, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.article_title
    }
}