package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bojanvilic.crvenazvezdainfo.R

class ShopPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_serbia_page, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        return root
    }
}