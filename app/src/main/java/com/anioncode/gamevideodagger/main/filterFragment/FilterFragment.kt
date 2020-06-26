package com.anioncode.gamevideodagger.main.filterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.filterFragment.viewModel.FilterViewModel
import com.anioncode.gamevideodagger.main.mainActivity.viewModel.VideoViewModel
import com.anioncode.gamevideodagger.model.popularModel.TopGames
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.FilterAdapter
import com.anioncode.smogu.Adapter.TopAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_filter.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import javax.inject.Inject

class FilterFragment : DaggerFragment() {


    lateinit var viewModel: FilterViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var adapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View= inflater.inflate(R.layout.fragment_filter, container, false)



        //        With ViewModelFactory
        viewModel = ViewModelProvider(this, providerFactory).get(FilterViewModel::class.java)

        viewModel.getInfoAboutGame("5","Witcher")

        viewModel.observeSingle()!!.observe(activity!!,
            Observer<SearchModel?> { t ->
                if (t != null) {

                    adapter.setPosts(t.results)

                }
            })

        val layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.filter.layoutManager = layoutManager
        view.filter.adapter = adapter

        return view
    }


}