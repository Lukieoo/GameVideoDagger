package com.anioncode.gamevideodagger.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.ranked.TopGames
import com.anioncode.gamevideodagger.ui.VideoViewModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.LatestAdapter
import com.anioncode.smogu.Adapter.TopAdapter
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    lateinit var viewModel: VideoViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var adapter: TopAdapter

    @Inject
    lateinit var adapter1: LatestAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View= inflater.inflate(R.layout.fragment_home, container, false)
        //viewModel = ViewModelProviders.of(this, providerFactory).get(VideoViewModel::class.java)
        viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

//        With ViewModelFactory
        val viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)
        viewModel.authenticateWithId("2019-01-01,2020-05-31")
        viewModel.authenticateWithString("2020-01-01,2020-12-31")

        initRecyclerView(view);
        initRecyclerView1(view);
        subscribeObservers()

        return view
    }

    private fun subscribeObservers() {
        viewModel.observeUser()!!.observe(activity!!, object : Observer<TopGames?> {
            override fun onChanged(t: TopGames?) {
                if (t != null) {

                    adapter.setPosts(t.results)
                    println("Holer ER1")

                }
            }
        })
        viewModel.observeLatestGames()!!.observe(activity!!, object : Observer<TopGames?> {
            override fun onChanged(t: TopGames?) {
                if (t != null) {
                    adapter1.setPosts(t.results)
                    println("Holer ER2")
                }
            }
        })
    }
    private fun initRecyclerView(view:View) {
        val layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        view.recyclerView.setLayoutManager(layoutManager)
        view.recyclerView.setAdapter(adapter)

    }

    private fun initRecyclerView1(view:View) {

        view.recyclerView1.setLayoutManager(GridLayoutManager(activity, 3))
        view.recyclerView1.setAdapter(adapter1)
    }

}