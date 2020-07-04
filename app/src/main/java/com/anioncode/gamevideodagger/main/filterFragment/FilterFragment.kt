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
import com.anioncode.gamevideodagger.model.plaformModel.platformModel
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.FilterAdapter
import com.anioncode.smogu.Adapter.GangerAdapter
import com.anioncode.smogu.Adapter.PlatformAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.bottomsheetdialog.view.*
import kotlinx.android.synthetic.main.bottomsheetdialog.view.dialogContainer
import kotlinx.android.synthetic.main.bottomsheetdialog_page.view.*
import kotlinx.android.synthetic.main.fragment_filter.view.*
import javax.inject.Inject

class FilterFragment : DaggerFragment() {


    lateinit var viewModel: FilterViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: FilterAdapter

    @Inject
    lateinit var adapter1: GangerAdapter


    @Inject
    lateinit var adapter2: PlatformAdapter

    //TODO When you want more games in list
    companion object {
        var isEnd = false
        var page_numer = 1
        var countNumber = 0
        var max_page = 0
        var nameSearched = ""
        var geners: String = ""
        var platform: String = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_filter, container, false)

        //        With ViewModelFactory

        view.refreshLayout.setOnRefreshListener {
            view.refreshLayout.isRefreshing = false
            isEnd = false
        }

        viewModel = ViewModelProvider(this, providerFactory).get(FilterViewModel::class.java)

//        viewModel.getInfoAboutGame("20", "Fornite")
//
        viewModel.observeSingle()!!.observe(activity!!,
            Observer<SearchModel?> { t ->
                if (t != null) {

                    countNumber = t.count

                    if (countNumber % 25 == 0) {
                        max_page = countNumber / 25
                    } else {
                        max_page = countNumber / 25 + 1
                    }

                    //    isEnd = adapter.itemCount >= countNumber


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
        //platform
        viewModel.observePlatform()!!.observe(activity!!,
            Observer<platformModel?> { t ->
                if (t != null) {
                    adapter2.setPosts(t.results)

                }
            })

        if (adapter1.itemCount == 0) {
            viewModel.getGenreGame()
        }
        if (adapter2.itemCount == 0) {
            viewModel.getPlatformGame()
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
        view.filter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {

                    if (!isEnd) {
                        if (adapter.itemCount > 0) {
                            isEnd = true


                            bottomSheetDialogSite(container, view)
                        }
                        //     Toast.makeText(ProductsActivity.this, "Last", Toast.LENGTH_LONG).show();
                    }
                }
            }
        })

        return view
    }

    private fun getMore(a: Int) {
        isEnd = true
        view!!.filter.scrollToPosition(0)
        page_numer += a

        if (geners.trim().isNotEmpty() && platform.trim().isEmpty()) {

            viewModel.getInfoAboutGame(
                "25",
                nameSearched,
                geners,
                page_numer

            )

        } else if (geners.trim().isNotEmpty() && platform.trim().isNotEmpty()) {
            viewModel.getInfoAboutGamewithPlatform(
                "25",
                nameSearched,
                if (geners.trim().equals(" ")) null else geners,
                page_numer,
                if (platform.trim().equals(" ")) null else platform
            )

        } else if (geners.trim().isEmpty() && platform.trim().isNotEmpty()) {
            viewModel.getInfoAboutGamewithPlatform2(
                "25",
                nameSearched,
                page_numer,
                if (platform.trim().equals(" ")) null else platform
            )
        } else {

            viewModel.getInfoAboutGame("25", nameSearched, page_numer)
        }
//        if (geners.trim().equals("")) {
//
//            viewModel.getInfoAboutGame("25", nameSearched, page_numer)
//        } else {
//            viewModel.getInfoAboutGame("25", nameSearched, geners, page_numer)
//        }
        //      viewModel.getInfoAboutGamewithPlatform("25", nameSearched, if(geners.trim().equals(" "))null else geners,page_numer,if(platform.trim().equals(" "))null else platform)
        //    }
    }

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
        viewlayout.platformRec.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewlayout.genresRec.adapter = adapter1

        viewlayout.platformRec.adapter = adapter2

        viewlayout.findButton.setOnClickListener {

            geners = ""
            platform = ""

            for (data in adapter1.getPost()) {
                if (data.clicked) {
                    geners += data.slug + ","
                }
            }

            for (data in adapter2.getPost()) {
                if (data.clicked) {
                    platform += data.id.toString() + ","
                }
            }
            nameSearched = viewlayout.textInsert.text.toString()
            page_numer = 1;
            println("THISS  $geners ,$platform ,")
            if (geners.trim().isNotEmpty() && platform.trim().isEmpty()) {
                println("THISS TWO1  ")

                viewModel.getInfoAboutGame(
                    "25",
                    viewlayout.textInsert.text.toString(),
                    geners,
                    page_numer
                )

            } else if (geners.trim().isNotEmpty() && platform.trim().isNotEmpty()) {
                println("THISS TWO2")
                viewModel.getInfoAboutGamewithPlatform(
                    "25",
                    viewlayout.textInsert.text.toString(),
                    geners,
                    page_numer,
                    platform
                )

            } else if (geners.trim().isEmpty() && platform.trim().isNotEmpty()) {
                println("THISS TWO3")
                viewModel.getInfoAboutGamewithPlatform2(
                    "25",
                    viewlayout.textInsert.text.toString(),
                    page_numer,
                    if (platform.trim().equals(" ")) null else platform
                )
            } else {

                println("THISS TWO4")
                viewModel.getInfoAboutGame("25", viewlayout.textInsert.text.toString(), page_numer)
            }
//
//            print("LOGDDD ${viewlayout.textInsert.text.toString()} , ${if (geners.trim().equals(" ")) null else geners},$page_numer ,${  if (platform.trim().equals(" ")) null else platform}")
//            viewModel.getInfoAboutGamewithPlatform(
//                    "25",
//                viewlayout.textInsert.text.toString(),
//                if (geners.trim().equals(" ")) null else geners,
//                page_numer,
//                if (platform.trim().equals(" ")) null else platform
//            )

            view.refreshLayout.isRefreshing = true
            botomSheetDialog.dismiss()

            view.av_from_code.visibility = View.GONE
            view.gameFind.visibility = View.GONE

            // nameSearched=viewlayout.textInsert.text.toString()


        }


        botomSheetDialog.setContentView(viewlayout)
        botomSheetDialog.show()
    }

    private fun bottomSheetDialogSite(container: ViewGroup?, view: View) {
        var botomSheetDialog: BottomSheetDialog =
            BottomSheetDialog(activity!!, R.style.SheetDialog)
        var viewlayout: View = LayoutInflater.from(container!!.context)
            .inflate(R.layout.bottomsheetdialog_page, view.dialogContainer)
        viewlayout.page.text = "$page_numer/$max_page"

        viewlayout.siteDown.setOnClickListener {
            if (page_numer > 1) {
                getMore(-1)

            }
            botomSheetDialog.dismiss()
        }
        viewlayout.siteUp.setOnClickListener {
            if (page_numer != max_page) {

                getMore(1)
            }
            botomSheetDialog.dismiss()
        }

        botomSheetDialog.setContentView(viewlayout)
        botomSheetDialog.show()

        botomSheetDialog.setOnDismissListener {
            isEnd = false
        }
    }

}