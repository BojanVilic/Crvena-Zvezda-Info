package com.bojanvilic.crvenazvezdainfo.ui


import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.adapters.DetailRecyclerViewAdapter
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomeViewModel
import com.bojanvilic.crvenazvezdainfo.util.ConnectivityCheck
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article_detail_view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ArticleDetailViewFragment : Fragment() {

    private lateinit var article : ArticleModelRoom
    private val viewModel by sharedViewModel<HomeViewModel>()

    private var recyclerViewAdapter : DetailRecyclerViewAdapter = DetailRecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.connectivityAvailable = ConnectivityCheck.isConnected(context!!)
        viewModel.getOnlineArticles().observe(this, Observer<List<ArticleModelRoom>> {
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
        article = arguments?.getSerializable("article") as ArticleModelRoom
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val recyclerView : RecyclerView = root.findViewById(R.id.horizontal_recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            title_text.setText(Html.fromHtml(article.title , Html.FROM_HTML_MODE_LEGACY))
        }
        Picasso.get().load(article.imageUrl).into(article_image)
        val contentText = article.content.split("</p>".toRegex(), 2)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            article_first_paragraph_text.setText(Html.fromHtml(contentText[0] , Html.FROM_HTML_MODE_LEGACY))
            article_rest_paragraphs_text.setText(Html.fromHtml(contentText[1] , Html.FROM_HTML_MODE_LEGACY))
        }
    }
}
