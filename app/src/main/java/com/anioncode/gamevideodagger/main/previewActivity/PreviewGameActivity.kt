package com.anioncode.gamevideodagger.main.previewActivity

import android.app.ProgressDialog
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.previewActivity.util.AppBarStateChangeListener
import com.anioncode.gamevideodagger.main.previewActivity.viewModel.SingleViewModel
import com.anioncode.gamevideodagger.model.gamemodel.InfoGame
import com.anioncode.gamevideodagger.model.ranked.Result
import com.anioncode.gamevideodagger.model.ranked.TopGames
import com.anioncode.gamevideodagger.viewmodels.VideoViewModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.ScreenAdapter
import com.anioncode.smogu.Adapter.TypeAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_preview_game.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import javax.inject.Inject


class PreviewGameActivity : BaseActivity() {

    private var ctlr: MediaController? = null

    @Inject
    lateinit var adapter1: ScreenAdapter

    @Inject
    lateinit var adapter2: TypeAdapter

    lateinit var gameData: String

    lateinit var gson: Gson
    lateinit var gameDataJSON: Result

    lateinit var viewModel: SingleViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_game)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar!!.setDisplayHomeAsUpEnabled(true))
        val navIcon: Drawable = toolbar.getNavigationIcon()!!

        gameData = intent.extras!!.getString("GameData").toString()


        gson = Gson()
        gameDataJSON = gson?.fromJson(gameData, Result::class.java)
        initRecyclerView()
        initViewPager();
        toolbar.setNavigationOnClickListener {
            finish()
        }

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

                if (state!!.name.equals("COLLAPSED")) {

//                    colapsing!!.title=gameDataJSON.name

                    titleGameToolbar.visibility = View.VISIBLE
                    rateGameToolbar.visibility = View.VISIBLE
                    starToolbar.visibility = View.VISIBLE

                    titleGame.visibility = View.INVISIBLE
                    rateGame.visibility = View.INVISIBLE
                    star.visibility = View.INVISIBLE

                } else if (state!!.name.equals("IDLE")) {

                    titleGameToolbar.visibility = View.INVISIBLE
                    rateGameToolbar.visibility = View.INVISIBLE
                    starToolbar.visibility = View.INVISIBLE

                    titleGame.visibility = View.VISIBLE
                    rateGame.visibility = View.VISIBLE
                    star.visibility = View.VISIBLE

                    if (ctlr != null) ctlr!!.hide()
                } else {

                    titleGameToolbar.visibility = View.INVISIBLE
                    rateGameToolbar.visibility = View.INVISIBLE
                    starToolbar.visibility = View.INVISIBLE

                    titleGame.visibility = View.VISIBLE
                    rateGame.visibility = View.VISIBLE
                    star.visibility = View.VISIBLE

                    if (ctlr != null) ctlr!!.hide()
                }
            }
        })

        cardstates.setBackgroundResource(R.drawable.cornerdrawable);

        setTitle("");

        titleGameToolbar.isSelected = true;  // Set focus to the textview
        titleGameToolbar.text = gameDataJSON.name
        rateGameToolbar.text = "${gameDataJSON.rating}/${gameDataJSON.rating_top}"

        text3.text = gameDataJSON.released
        titleGame.isSelected = true;
        titleGame.text = gameDataJSON.name
        rateGame.text = "${gameDataJSON.rating}/${gameDataJSON.rating_top}"
        Picasso.get()
            .load(gameDataJSON.background_image)
            .into(back)

        if (gameDataJSON.clip != null) {
            val uri: Uri = Uri.parse(gameDataJSON.clip.clip)



            clipGame.setVideoURI(uri)
            clipGame.seekTo(2);

            clipGame.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(mp: MediaPlayer) {
                    mp.setOnVideoSizeChangedListener { mp, width, height ->
                        /** add media controller*/
                        ctlr = MediaController(this@PreviewGameActivity)
                        clipGame.setMediaController(ctlr)
                        /* and set its position on screen*/

                        ctlr!!.setAnchorView(clipGame)
                        //                clipGame.requestFocus()
                    }
                }
            })


        } else {
            clipGame.visibility = View.GONE
            text0.visibility=View.GONE
        }


        viewModel = ViewModelProvider(this, providerFactory).get(SingleViewModel::class.java)
        viewModel.getInfoAboutGame(gameDataJSON.slug)

        subscribeObservers()
    }
    private fun subscribeObservers() {
        viewModel.observeSingle()!!.observe(this, object : Observer<InfoGame?> {
            override fun onChanged(t: InfoGame?) {
                if (t != null) {

                    description.text=t.description_raw


                }
            }
        })

    }
    private fun initViewPager() {
        viewPager.apply {
            adapter = adapter1
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            var compositePageTransformer = CompositePageTransformer()

            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                var r: Float = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            setPageTransformer(compositePageTransformer)
        }
        adapter1.setPosts(gameDataJSON.short_screenshots)
    }

    private fun initRecyclerView() {
        gangerGame.setLayoutManager(
            LinearLayoutManager(
                this@PreviewGameActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        gangerGame.setAdapter(adapter2)
        adapter2.setPosts(gameDataJSON.genres)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
