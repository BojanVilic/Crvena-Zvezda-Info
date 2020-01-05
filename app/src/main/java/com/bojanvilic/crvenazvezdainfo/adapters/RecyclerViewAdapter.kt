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
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.google.android.gms.ads.AdRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.banner_ad_recyclerview.view.*
import kotlinx.android.synthetic.main.single_article_layout.view.*


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    
    var dataList: List<ArticleModelRoom> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_article_layout, parent, false)
        return ViewHolder(view)

    }

    fun readArticles(articleList: List<ArticleModelRoom>) {
        dataList = articleList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (Build.VERSION.SDK_INT >= 24) {
            holder.title.text =
                Html.fromHtml(dataList.get(position).title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.title.text = Html.fromHtml(dataList.get(position).title)
        }

        Picasso.get().load(dataList.get(position).imageUrl).into(holder.image)

        holder.itemView.setOnClickListener { view ->
            val bundle: Bundle = bundleOf()
            bundle.putSerializable("article", dataList.get(position))
            view.findNavController()
                .navigate(R.id.showDetail, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.article_title
        val image = view.article_image
    }
}