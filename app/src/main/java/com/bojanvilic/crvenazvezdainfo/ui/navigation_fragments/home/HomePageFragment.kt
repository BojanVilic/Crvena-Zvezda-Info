package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bojanvilic.crvenazvezdainfo.adapters.RecyclerViewAdapter
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticleContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment(), IViewContract.View {

    private val viewModel by viewModels<HomeViewModel>()

    private var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel.connectivityAvailable = ConnectivityCheck.isConnected(requireContext())
//        viewModel.getOnlineArticles().observe(viewLifecycleOwner, Observer(recyclerViewAdapter::submitList))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    ArticleContent() {

                    }
                }
            }
        }


//        val root = inflater.inflate(R.layout.fragment_home_page, container, false)
//        val layoutManager = GridLayoutManager(context, 1)
//        val recyclerView : RecyclerView = root.findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = recyclerViewAdapter
//        val mAdView : AdView = root.findViewById(R.id.adView)
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
//        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }
}