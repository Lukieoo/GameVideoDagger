package com.anioncode.gamevideodagger.main.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.ranked.TopGames
import com.anioncode.gamevideodagger.viewmodels.VideoViewModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.LatestAdapter
import com.anioncode.smogu.Adapter.TopAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.date1
import java.text.SimpleDateFormat
import java.util.*
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

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getYear = SimpleDateFormat("yyyy")


        println("${(getYear.format(Date()).toInt()-2)} rembrantttttttt")

        view.date1.text="${(getYear.format(Date()).toInt()-2)}"
        view.date2.text="${(getYear.format(Date()).toInt()-1)}"
        view.date3.text="${getYear.format(Date())}"

        viewModel.authenticateWithId("${(getYear.format(Date()).toString().toInt())}-01-01,${sdf.format(Date())}")
        viewModel.authenticateWithString("${getYear.format(Date())}-01-01,${getYear.format(Date())}-12-31")

        view.date1.setOnClickListener {
            viewModel.authenticateWithId("${view.date1.text}-01-01,${view.date1.text}-12-31")
        }
        view.date2.setOnClickListener {
            viewModel.authenticateWithId("${view.date2.text}-01-01,${view.date2.text}-12-31")
        }
        view.date3.setOnClickListener {
            viewModel.authenticateWithId("${view.date3.text}-01-01,${sdf.format(Date())}")
        }
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

                }
            }
        })
        viewModel.observeLatestGames()!!.observe(activity!!, object : Observer<TopGames?> {
            override fun onChanged(t: TopGames?) {
                if (t != null) {
                    adapter1.setPosts(t.results)

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