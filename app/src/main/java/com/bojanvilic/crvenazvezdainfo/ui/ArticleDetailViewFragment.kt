package com.bojanvilic.crvenazvezdainfo.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.adapters.DetailRecyclerViewAdapter
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomeViewModel
import com.bojanvilic.crvenazvezdainfo.util.ConnectivityCheck
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article_detail_view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView
import java.text.SimpleDateFormat
import java.util.*


class ArticleDetailViewFragment : Fragment() {

    private lateinit var article : ArticleModelRoom
    private val viewModel by sharedViewModel<HomeViewModel>()
    private lateinit var htmlTextView: HtmlTextView

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
        htmlTextView = root.findViewById(R.id.article_rest_paragraphs_text)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter

        val mFirstLargeAd : AdView = root.findViewById(R.id.firstLargeAd)
        val largeAdRequest = AdRequest.Builder().build()
        mFirstLargeAd.loadAd(largeAdRequest)
        val mAfterImageAd : AdView = root.findViewById(R.id.bannerAfterImageAd)
        val afterImageAdRequest = AdRequest.Builder().build()
        mAfterImageAd.loadAd(afterImageAdRequest)
        val mAfterArticleAd : AdView = root.findViewById(R.id.bannerAfterArticleAd)
        val afterArticleAdRequest = AdRequest.Builder().build()
        mAfterArticleAd.loadAd(afterArticleAdRequest)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title_text.setText(HtmlCompat.fromHtml(article.title, HtmlCompat.FROM_HTML_MODE_COMPACT))

        Picasso.get().load(article.imageUrl).into(article_image)
        val contentText = article.content.split("</p>".toRegex(), 2)
        article_first_paragraph_text.setText(HtmlCompat.fromHtml(contentText[0] , HtmlCompat.FROM_HTML_MODE_COMPACT))
        htmlTextView.setHtml(contentText[1], HtmlHttpImageGetter(htmlTextView))

        Log.d("ARTICLE", HtmlCompat.fromHtml(article.content, HtmlCompat.FROM_HTML_MODE_COMPACT).toString())

        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY)
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
        val formattedDate = formatter.format(parser.parse(article.date))

        dateTextView.text = formattedDate
    }

}
