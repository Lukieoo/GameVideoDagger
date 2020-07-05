package com.anioncode.gamevideodagger.main.databaseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.anioncode.gamevideodagger.R
//import com.anioncode.gamevideodagger.main.databaseFragment.data.Product
//import com.anioncode.gamevideodagger.main.databaseFragment.repository.ProductRepository
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class DatabaseFragment :DaggerFragment() {

//    @Inject
//    var productRepository: ProductRepository? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            var view: View = inflater.inflate(R.layout.fragment_database, container, false)

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