package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.adapters.RecyclerViewAdapter
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.di.Injector
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.viewmodel.ArticleViewModel

class HomePageFragment : Fragment(), IViewContract.View {

    lateinit var presenter: IViewContract.Presenter
    lateinit var viewModel: ArticleViewModel
    private var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home_page, container, false)
        val layoutManager = GridLayoutManager(context, 3)
        val recyclerView : RecyclerView = root.findViewById(R.id.recyclerView)

        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        viewModel.getArticles().observe(this, Observer<List<Model.Article>> {
            photos ->
            run {
                recyclerViewAdapter.readArticles(photos)
                Log.d("PROBA", photos[0].title.toString())
            }
        })
        //recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = recyclerViewAdapter
        return root
    }

    companion object {
        fun newInstance(injector : Injector) = HomePageFragment().setPresenter(injector)
    }

    private fun setPresenter(injector:Injector):Fragment{
        presenter = injector.providePresenter(this)
        presenter.bind()
        return this
    }

    override fun onStart() {
        super.onStart()
    }
}