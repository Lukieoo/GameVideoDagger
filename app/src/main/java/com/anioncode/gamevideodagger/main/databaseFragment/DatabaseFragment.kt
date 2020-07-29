package com.anioncode.gamevideodagger.main.databaseFragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.databaseFragment.data.WordViewModel
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.RoomDbAdapter
//import com.anioncode.gamevideodagger.main.databaseFragment.data.Product
//import com.anioncode.gamevideodagger.main.databaseFragment.repository.ProductRepository
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_database.view.*
import javax.inject.Inject


class DatabaseFragment :DaggerFragment() {


    private lateinit var wordViewModel: WordViewModel


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapterDb: RoomDbAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            var view: View = inflater.inflate(R.layout.fragment_database, container, false)


        wordViewModel = ViewModelProvider(this,providerFactory).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(DatabaseFragment@this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                println("$it it")
                adapterDb.setPosts(it)
            }
        })
//



       // wordViewModel.deleteAll()

        view.myGamesRec.apply {
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
            adapter = adapterDb
        }
//        productRepository!!.findAll()
//            .observe(viewLifecycleOwner,
//                Observer<List<Product?>?> { products ->
//                    Toast.makeText(
//                        activity,
//                        String.format("Product size: %s", products!!.size),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                })

            return view
        }
    }