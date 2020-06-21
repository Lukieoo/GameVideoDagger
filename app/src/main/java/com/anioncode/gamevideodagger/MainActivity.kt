package com.anioncode.gamevideodagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anioncode.gamevideodagger.model.ranked.TopGames
import com.anioncode.gamevideodagger.ui.VideoViewModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.TopAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    lateinit var viewModel: VideoViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var  adapter:TopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //viewModel = ViewModelProviders.of(this, providerFactory).get(VideoViewModel::class.java)
        viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

        // With ViewModelFactory
      //  val viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

        viewModel.authenticateWithId("2019-01-01,2019-12-31")

        initRecyclerView();
        subscribeObservers()

    }


    private fun subscribeObservers() {
       // if (::viewModel.isInitialized){
            viewModel.observeUser()!!.observe(this, object : Observer<TopGames?> {
                override fun onChanged(t: TopGames?) {
                    if (t != null) {
                        text.text="${t.results.get(0).name}"
                        adapter.setPosts(t.results)
                    }
                }
            })
     //   }


    }

    private fun initRecyclerView() {
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(adapter)
    }
}
