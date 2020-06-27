package com.anioncode.gamevideodagger.main.filterFragment

import android.graphics.Color
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.bottomsheetdialog.view.*
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

        var view: View = inflater.inflate(R.layout.fragment_filter, container, false)

        //        With ViewModelFactory
        viewModel = ViewModelProvider(this, providerFactory).get(FilterViewModel::class.java)

//        viewModel.getInfoAboutGame("20", "Fornite")
//
        viewModel.observeSingle()!!.observe(activity!!,
            Observer<SearchModel?> { t ->
                if (t != null) {


                    adapter.setPosts(t.results)

                }
            })

        view.filterOpen.setOnClickListener {
            var botomSheetDialog:BottomSheetDialog=BottomSheetDialog(activity!!,R.style.SheetDialog)
            var viewlayout:View=LayoutInflater.from(container!!.context).inflate(R.layout.bottomsheetdialog,view.dialogContainer)

            viewlayout.findButton.setOnClickListener {
                viewModel.getInfoAboutGame("20",   viewlayout.textInsert.text.toString())
                botomSheetDialog.dismiss()
            }

//            viewlayout.numberpicker.maxValue=30
//            viewlayout.numberpicker.minValue=1
//
//            viewlayout.numberpicker.setOnValueChangedListener { picker, oldVal, newVal ->
//
//            }

            botomSheetDialog.setContentView(viewlayout)
            botomSheetDialog.show()


        }

        ///Todo change this
        if (!::adapter.isInitialized) view.gameFind.visibility = View.VISIBLE
        else view.gameFind.visibility = View.GONE

        val layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.filter.layoutManager = layoutManager
        view.filter.adapter = adapter

        return view
    }


}