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
import com.anioncode.gamevideodagger.main.mainActivity.viewModel.VideoViewModel
import com.anioncode.gamevideodagger.model.popularModel.TopGames
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.LatestAdapter
import com.anioncode.smogu.Adapter.TopAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
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

        var view:View= inflater.inflate(R
            .layout.fragment_home, container, false)

        initViewModel(view)
        initRecyclerView(view);
        initRecyclerView1(view);
        subscribeObservers()

        return view
    }

    private fun initViewModel(view: View) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getYear = SimpleDateFormat("yyyy")
        val getMonth = SimpleDateFormat("MM")

        val newDate = Date(Date().time - 2592000000L)

//        view.date1.text = "${(getYear.format(Date()).toInt() - 2)}"
//        view.date2.text = "${(getYear.format(Date()).toInt() - 1)}"
//        view.date3.text = "${getYear.format(Date())}"



        //        With ViewModelFactory
        viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)


        if (adapter.itemCount ==0)
        {

            var res=(getMonth.format(Date()).toString().toInt()-1).toString()



            if(res.length<2){
                res="0$res"
            }

            viewModel.authenticateWithId(
                "${sdf.format(newDate)},${sdf.format(
                    Date()
                )}"
            )
            viewModel.authenticateWithString("${getYear.format(Date())}-01-01,${getYear.format(Date())}-12-31")

        }




// Popular date per Year
//        view.date1.setOnClickListener {
//            viewModel.authenticateWithId("${view.date1.text}-01-01,${view.date1.text}-12-31")
//            view.date1.setBackgroundColor(Color.parseColor("#000000"))
//            view.date2.setBackgroundColor(Color.parseColor("#FFFFFF"))
//            view.date3.setBackgroundColor(Color.parseColor("#FFFFFF"))
//        }
//        view.date2.setOnClickListener {
//            viewModel.authenticateWithId("${view.date2.text}-01-01,${view.date2.text}-12-31")
//            view.date1.setBackgroundColor(Color.parseColor("#FFFFFF"))
//            view.date2.setBackgroundColor(Color.parseColor("#000000"))
//            view.date3.setBackgroundColor(Color.parseColor("#FFFFFF"))
//        }
//        view.date3.setOnClickListener {
//            viewModel.authenticateWithId("${view.date3.text}-01-01,${view.date3.text}-12-31")
//            view.date1.setBackgroundColor(Color.parseColor("#FFFFFF"))
//            view.date2.setBackgroundColor(Color.parseColor("#FFFFFF"))
//            view.date3.setBackgroundColor(Color.parseColor("#000000"))
//        }
    }

    private fun subscribeObservers() {

        viewModel.observeGaneInfo()!!.observe(activity!!,
            Observer<TopGames?> { t ->
                if (t != null) {

                    adapter.setPosts(t.results)

                }
            })
        viewModel.observeLatestGames()!!.observe(activity!!,
            Observer<TopGames?> { t ->
                if (t != null) {
                    adapter1.setPosts(t.results)

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