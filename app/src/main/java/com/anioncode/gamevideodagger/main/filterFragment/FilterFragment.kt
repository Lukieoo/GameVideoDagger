package com.anioncode.gamevideodagger.main.filterFragment

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

class FilterFragment : DaggerFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View= inflater.inflate(R.layout.fragment_filter, container, false)


        return view
    }


}