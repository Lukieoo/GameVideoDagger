package com.anioncode.gamevideodagger.main

import android.R.color
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.anioncode.gamevideodagger.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_preview_game.*
import java.util.*


class PreviewGameActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_game)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar!!.setDisplayHomeAsUpEnabled(true))
        val navIcon: Drawable = toolbar.getNavigationIcon()!!


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            navIcon.setColorFilter(BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP));
        } else {
            navIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        }

        cardstates.setBackgroundResource(R.drawable.cornerdrawable);
        setTitle("");

        Picasso.get()
            .load(intent.extras!!.getString("GameListBackround"))
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(back);
    }
}