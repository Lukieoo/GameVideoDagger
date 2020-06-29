package com.anioncode.gamevideodagger.main.filterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.filterFragment.viewModel.FilterViewModel
import com.anioncode.gamevideodagger.model.genresModel.genresModel
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.FilterAdapter
import com.anioncode.smogu.Adapter.GangerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_preview_game.*
import kotlinx.android.synthetic.main.bottomsheetdialog.view.*
import kotlinx.android.synthetic.main.fragment_filter.view.*
import javax.inject.Inject

class FilterFragment : DaggerFragment() {


    lateinit var viewModel: FilterViewModel
    lateinit var viewModel2: FilterViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: FilterAdapter

    @Inject
    lateinit var adapter1: GangerAdapter

    //TODO When you want more games in list
//    companion object{
//        var isEnd = false
//        var currentSize = 20
//        var countNumber = 0
//        var nameSearched = ""
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_filter, container, false)

        //        With ViewModelFactory

        view.refreshLayout.setOnRefreshListener { view.refreshLayout.isRefreshing = false }

        viewModel = ViewModelProvider(this, providerFactory).get(FilterViewModel::class.java)

//        viewModel.getInfoAboutGame("20", "Fornite")
//
        viewModel.observeSingle()!!.observe(activity!!,
            Observer<SearchModel?> { t ->
                if (t != null) {
//TODO When you want more games in list
//                    countNumber= t.count
//                    isEnd = adapter.itemCount>=countNumber
//

                    view.refreshLayout.isRefreshing = false

                    adapter.setPosts(t.results)

                }
            })

        //genre type
        viewModel.observeGenre()!!.observe(activity!!,
            Observer<genresModel?> { t ->
                if (t != null) {
                    adapter1.setPosts(t.results)

                }
            })

        if (adapter1.itemCount == 0) {
            viewModel.getGenreGame()
        }
        //end Genre
        view.av_from_code.setOnClickListener {
            bottomSheetDialog(container, view)
        }

        view.filterOpen.setOnClickListener {
            bottomSheetDialog(container, view)

        }

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


        //TODO When you want more games in list
//        view.filter.addOnScrollListener(object :RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollVertically(1)) {
//
//                    if (!isEnd) {
//                        getMore()
//                        //     Toast.makeText(ProductsActivity.this, "Last", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        })

        return view
    }

//    private fun getMore() {
//        currentSize+=20
//        viewModel.getInfoAboutGame((currentSize).toString(), nameSearched)
//    }

    private fun bottomSheetDialog(container: ViewGroup?, view: View) {
        var botomSheetDialog: BottomSheetDialog =
            BottomSheetDialog(activity!!, R.style.SheetDialog)
        var viewlayout: View = LayoutInflater.from(container!!.context)
            .inflate(R.layout.bottomsheetdialog, view.dialogContainer)

        viewlayout.genresRec.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewlayout.genresRec.setAdapter(adapter1)
        viewlayout.findButton.setOnClickListener {
            var geners:String=""
            for (data in adapter1.getPost()) {
                if (data.clicked){
                    geners+=data.slug+","
                }
            }

            if (geners.trim().isNotEmpty()){
                println("THISS ONE https://api.rawg.io/api/games?pagr_size=25&search=${viewlayout.textInsert.text.toString()}&genres=$geners")
                viewModel.getInfoAboutGame("25", viewlayout.textInsert.text.toString(),geners)

            }else{
                println("THISS TWO")
                viewModel.getInfoAboutGame("25", viewlayout.textInsert.text.toString())
            }

            view.refreshLayout.isRefreshing = true
            botomSheetDialog.dismiss()

            view.av_from_code.visibility = View.GONE
            view.gameFind.visibility = View.GONE

            // nameSearched=viewlayout.textInsert.text.toString()


        }


        botomSheetDialog.setContentView(viewlayout)
        botomSheetDialog.show()
    }


}