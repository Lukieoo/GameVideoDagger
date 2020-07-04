package com.anioncode.gamevideodagger.main.mainActivity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.Utiles.NetworkState
import com.anioncode.gamevideodagger.main.ConnectionFragment.ConnectionFragment
import com.anioncode.gamevideodagger.main.databaseFragment.DatabaseFragment
import com.anioncode.gamevideodagger.main.filterFragment.FilterFragment
import com.anioncode.gamevideodagger.main.homeFragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (NetworkState.isNetworkAvailable(this@MainActivity)) {

            supportFragmentManager.beginTransaction().replace(R.id.myframe, HomeFragment()).commit()

        }else{

            supportFragmentManager.beginTransaction().replace(R.id.myframe, ConnectionFragment()).commit()

        }
        bottom_navigation.setOnNavigationItemSelectedListener(this@MainActivity)


    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when (p0.itemId) {
            R.id.home -> {

                if (NetworkState.isNetworkAvailable(this@MainActivity)) {
                    supportFragmentManager.beginTransaction().replace(R.id.myframe, HomeFragment())
                        .commit()
                }else{

                    supportFragmentManager.beginTransaction().replace(R.id.myframe, ConnectionFragment()).commit()

                }
                return true
            }
            R.id.dashboard -> {
                if (NetworkState.isNetworkAvailable(this@MainActivity)) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.myframe, FilterFragment())
                        .commit()
                }else{

                    supportFragmentManager.beginTransaction().replace(R.id.myframe, ConnectionFragment()).commit()

                }
                return true
            }
            R.id.account -> {
                supportFragmentManager.beginTransaction().replace(R.id.myframe, DatabaseFragment()).commit()
                return true
            }
            else -> { // Note the block
                return true
            }
        }
        return true
    }

}
