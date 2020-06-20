package com.anioncode.gamevideodagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anioncode.gamevideodagger.model.tmpUsersItem
import com.anioncode.gamevideodagger.ui.VideoViewModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    lateinit var viewModel: VideoViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //viewModel = ViewModelProviders.of(this, providerFactory).get(VideoViewModel::class.java)
        viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

        // With ViewModelFactory
      //  val viewModel = ViewModelProvider(this, providerFactory).get(VideoViewModel::class.java)

        viewModel.authenticateWithId(3);
        subscribeObservers()
    }


    private fun subscribeObservers() {
       // if (::viewModel.isInitialized){
            viewModel.observeUser()!!.observe(this, object : Observer<tmpUsersItem?> {
                override fun onChanged(t: tmpUsersItem?) {
                    if (t != null) {
                        text.text="${t.username}"
                    }
                }
            })
     //   }


    }
}
