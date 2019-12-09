package com.bojanvilic.crvenazvezdainfo.adapters

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.squareup.picasso.Picasso
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.single_article_layout.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var dataList: List<Model.Article> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_article_layout, parent, false)
        return ViewHolder(view)
    }

    fun readArticles(articleList: List<Model.Article>) {
        dataList = articleList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= 24) {
            holder.title.text =
                Html.fromHtml(dataList.get(position).title.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.title.text = Html.fromHtml(dataList.get(position).title.title)
        }
        Picasso.get().load(dataList.get(position)._embedded.wpFeaturedmedia[0].source_url)
            .into(holder.image)

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