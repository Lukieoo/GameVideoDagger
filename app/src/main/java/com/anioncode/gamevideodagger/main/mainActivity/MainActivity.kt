package com.anioncode.gamevideodagger.main.mainActivity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.filterFragment.FilterFragment
import com.anioncode.gamevideodagger.main.homeFragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,
    BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.myframe, HomeFragment()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener(this@MainActivity)


    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when (p0.itemId) {
            R.id.home -> {
                print("deto1")
                supportFragmentManager.beginTransaction().replace(R.id.myframe, HomeFragment()).commit()
                return  true
            }
            R.id.dashboard -> {
                print("deto2")
                supportFragmentManager.beginTransaction().replace(R.id.myframe, FilterFragment()).commit()
                return true
            }
            R.id.account -> {
                print("deto3")
                supportFragmentManager.beginTransaction().replace(R.id.myframe, HomeFragment()).commit()
                return   true
            }
            else -> { // Note the block
                return   true
            }
        }
        return    true
    }

}
