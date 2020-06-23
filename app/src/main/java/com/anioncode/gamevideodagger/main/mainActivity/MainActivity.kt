package com.anioncode.gamevideodagger.main.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.homeFragment.HomeFragment
import dagger.android.AndroidInjection


class MainActivity : AppCompatActivity() {


//    lateinit var viewModel: VideoViewModel
//
//    @Inject
//    lateinit var providerFactory: ViewModelProviderFactory
//
//    @Inject
//    lateinit var  adapter:TopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.myframe, HomeFragment()).commit()
        //viewModel = ViewModelProviders.of(this, providerFactory).get(VideoViewModel::class.java)
//        viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

        // With ViewModelFactory
        //  val viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

//        viewModel.authenticateWithId("2020-01-01,2020-12-31")
//
//        initRecyclerView();
//        subscribeObservers()

    }
//
//
//    private fun subscribeObservers() {
//       // if (::viewModel.isInitialized){
//            viewModel.observeUser()!!.observe(this, object : Observer<TopGames?> {
//                override fun onChanged(t: TopGames?) {
//                    if (t != null) {
//
//                        adapter.setPosts(t.results)
//                    }
//                }
//            })
//     //   }
//
//
//    }
//
//    private fun initRecyclerView() {
//        val layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.setLayoutManager(layoutManager)
//        recyclerView.setAdapter(adapter)
//    }
}
