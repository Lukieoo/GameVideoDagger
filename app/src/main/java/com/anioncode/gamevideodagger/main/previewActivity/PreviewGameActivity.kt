package com.anioncode.gamevideodagger.main.previewActivity

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.previewActivity.util.AppBarStateChangeListener
import com.anioncode.gamevideodagger.model.ranked.Result
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_preview_game.*
import org.json.JSONObject
import java.util.*


class PreviewGameActivity : BaseActivity(){
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {

         var gameData: String

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_game)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar!!.setDisplayHomeAsUpEnabled(true))
        val navIcon: Drawable = toolbar.getNavigationIcon()!!

        gameData= intent.extras!!.getString("GameData").toString()


        var gson = Gson()
        var gameDataJSON = gson?.fromJson(gameData, Result::class.java)

        println("LOGDDDDDDD $gameData")


        toolbar.setNavigationOnClickListener{
            finish()
        }
//
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            navIcon.setColorFilter(BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP));
        } else {
            navIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        }

        appbarlayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(
                appBarLayout: AppBarLayout?,
                state: State?
            ) {

                colapsing.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);

                colapsing.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
                colapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
                Log.d("STATE", state!!.name)

                if ( state!!.name.equals("COLLAPSED")){

                    colapsing!!.title=gameDataJSON.name
                    titleGame.visibility= View.INVISIBLE
                    rateGame.visibility= View.INVISIBLE
                    star.visibility= View.INVISIBLE

                }else if( state!!.name.equals("IDLE")){
                    colapsing!!.title=""
                    titleGame.visibility= View.VISIBLE
                    rateGame.visibility= View.VISIBLE
                    star.visibility= View.VISIBLE
                }
                else{
                    colapsing!!.title=""
                    titleGame.visibility= View.VISIBLE
                    rateGame.visibility= View.VISIBLE
                    star.visibility= View.VISIBLE
                }
            }
        })
        cardstates.setBackgroundResource(R.drawable.cornerdrawable);
        setTitle("");

        titleGame.text=gameDataJSON.name
        rateGame.text="${gameDataJSON.rating}/${gameDataJSON.rating_top}"
        Picasso.get()
            .load(gameDataJSON.background_image)
            //.networkPolicy(NetworkPolicy.OFFLINE)
            .into(back);
    }
}
