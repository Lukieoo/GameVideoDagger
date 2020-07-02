package com.anioncode.gamevideodagger.main.ConnectionFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anioncode.gamevideodagger.R
import dagger.android.support.DaggerFragment

class ConnectionFragment  : DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_connection, container, false)

        return view
    }
}