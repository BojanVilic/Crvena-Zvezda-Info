package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.football

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.adapters.RecyclerViewAdapter
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.ConnectivityCheck
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import org.koin.android.viewmodel.ext.android.viewModel

class FootballPageFragment : Fragment() {

    lateinit var presenter: IViewContract.Presenter
    private val viewModel by viewModel<FootballViewModel>()
    private var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_football_page, container, false)
        val layoutManager = GridLayoutManager(context, 1)
        val recyclerView : RecyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
        val mAdView : AdView = root.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.connectivityAvailable = ConnectivityCheck.isConnected(context!!)
        viewModel.getOnlineArticles().observe(this, Observer(recyclerViewAdapter::submitList))
    }


}