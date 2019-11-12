package com.bojanvilic.crvenazvezdainfo.adapters

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_article_layout.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var dataList : List<Model.Article>
    var image : MutableList<Model.ImageModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.single_article_layout, parent, false)
        return ViewHolder(view)
    }

    fun readArticles(articleList : List<Model.Article>){
        dataList = articleList
        notifyDataSetChanged()
    }

    fun readImage(image : Model.ImageModel) {
        this.image.add(image)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= 24)
        {
            holder.title.text = Html.fromHtml(dataList.get(position).title.title , Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.title.text = Html.fromHtml(dataList.get(position).title.title)
        }
        if(image.size > position) {
            Picasso.get().load(image.get(position).guid.imageUrl).into(holder.image)
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var v : View = view
        val title = view.article_title
        val image = view.article_image

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

        }
    }
}