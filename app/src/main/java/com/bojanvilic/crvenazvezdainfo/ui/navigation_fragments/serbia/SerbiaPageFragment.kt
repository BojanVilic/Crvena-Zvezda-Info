package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.serbia

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
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import org.koin.android.viewmodel.ext.android.viewModel

class SerbiaPageFragment : Fragment() {

    lateinit var presenter: IViewContract.Presenter
    private val viewModel by viewModel<SerbiaViewModel>()
    private var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_serbia_page, container, false)
        val layoutManager = GridLayoutManager(context, 3)
        val recyclerView : RecyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getArticles().observe(this, Observer<List<Model.Article>> {
                articles ->
            run {
                recyclerViewAdapter.readArticles(articles)
            }
        })
    }
}