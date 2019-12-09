package com.bojanvilic.crvenazvezdainfo.ui


import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.adapters.DetailRecyclerViewAdapter
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main_page.*
import kotlinx.android.synthetic.main.fragment_article_detail_view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ArticleDetailViewFragment : Fragment() {

    private lateinit var article : Model.Article
    private val viewModel by sharedViewModel<HomeViewModel>()

    private var recyclerViewAdapter : DetailRecyclerViewAdapter = DetailRecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getArticles().observe(this, Observer<List<Model.Article>> {
                articles ->
            run {
                recyclerViewAdapter.readArticles(articles)
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_article_detail_view, container, false)
        article = arguments?.getSerializable("article") as Model.Article
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val recyclerView : RecyclerView = root.findViewById(R.id.horizontal_recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", article.categories.get(0).toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            title_text.setText(Html.fromHtml(article.title.title , Html.FROM_HTML_MODE_LEGACY))
        }
        Picasso.get().load(article._embedded.wpFeaturedmedia[0].source_url).into(article_image)
        val contentText = article.content.article_text.split("</p>".toRegex(), 2)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            article_first_paragraph_text.setText(Html.fromHtml(contentText[0] , Html.FROM_HTML_MODE_LEGACY))
            article_rest_paragraphs_text.setText(Html.fromHtml(contentText[1] , Html.FROM_HTML_MODE_LEGACY))
        }
    }
}
