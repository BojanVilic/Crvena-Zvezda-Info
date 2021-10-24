package com.bojanvilic.crvenazvezdainfo.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.banner_ad_recyclerview.view.*
import kotlinx.android.synthetic.main.fragment_article_detail_view.view.*
import kotlinx.android.synthetic.main.single_article_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class RecyclerViewAdapter : PagedListAdapter<ArticleModelRoom, RecyclerViewAdapter.ViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener { view ->
            val bundle: Bundle = bundleOf()
            bundle.putSerializable("article", getItem(position))
            view.findNavController()
                .navigate(R.id.showDetail, bundle)
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_article_layout, parent, false)) {

        private val articleTitle = itemView.findViewById<TextView>(R.id.article_title)
        private val articleImage = itemView.findViewById<ImageView>(R.id.article_image)
        private val articleDate = itemView.findViewById<TextView>(R.id.article_date)
        var article : ArticleModelRoom? = null


        fun bindTo(article : ArticleModelRoom?) {
            this.article = article
            articleTitle.text = article?.title?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT) }
            Picasso.get().load(article?.imageUrl).into(articleImage)
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY)
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
            val output: String = formatter.format(parser.parse(article!!.date)!!)
            val date: String = output.split("\\s+".toRegex())[0]
            val time: String = output.split("\\s+".toRegex())[1]
            val articleFormattedDate = date + "\n" + time
            articleDate.text = articleFormattedDate
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ArticleModelRoom>() {
            override fun areItemsTheSame(oldItem: ArticleModelRoom, newItem: ArticleModelRoom): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleModelRoom, newItem: ArticleModelRoom): Boolean =
                oldItem == newItem
        }
    }
}