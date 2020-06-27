package com.anioncode.gamevideodagger.main.filterFragment

import android.animation.Animator
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
        view.av_from_code.setOnClickListener {
            bottomSheetDialog(container, view)
        }
        view.av_from_code.playAnimation()
        view.filterOpen.setOnClickListener {
            bottomSheetDialog(container, view)

        }

        ///Todo change this
        if (adapter.itemCount == 0) {
            view.av_from_code.visibility = View.VISIBLE
            view.gameFind.visibility = View.VISIBLE
        } else {
            view.av_from_code.visibility = View.GONE
            view.gameFind.visibility = View.GONE
        }

        val layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.filter.layoutManager = layoutManager
        view.filter.adapter = adapter

        return view
    }

    private fun bottomSheetDialog(container: ViewGroup?, view: View) {
        var botomSheetDialog: BottomSheetDialog =
            BottomSheetDialog(activity!!, R.style.SheetDialog)
        var viewlayout: View = LayoutInflater.from(container!!.context)
            .inflate(R.layout.bottomsheetdialog, view.dialogContainer)

        viewlayout.findButton.setOnClickListener {
            viewModel.getInfoAboutGame("20", viewlayout.textInsert.text.toString())
            botomSheetDialog.dismiss()

            view.av_from_code.visibility = View.GONE
            view.gameFind.visibility = View.GONE
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


}