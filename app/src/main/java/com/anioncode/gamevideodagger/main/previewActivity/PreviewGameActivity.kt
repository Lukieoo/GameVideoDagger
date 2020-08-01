package com.anioncode.gamevideodagger.main.previewActivity

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.databaseFragment.data.WordViewModel
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import com.anioncode.gamevideodagger.main.previewActivity.util.AppBarStateChangeListener
import com.anioncode.gamevideodagger.main.previewActivity.viewModel.SingleViewModel
import com.anioncode.gamevideodagger.model.detailModel.InfoGame
import com.anioncode.gamevideodagger.model.popularModel.PlatformX
import com.anioncode.gamevideodagger.model.popularModel.Result
import com.anioncode.gamevideodagger.viewmodels.ViewModelProviderFactory
import com.anioncode.smogu.Adapter.ScreenAdapter
import com.anioncode.smogu.Adapter.TypeAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_preview_game.*
import java.util.*
import javax.inject.Inject


class PreviewGameActivity : BaseActivity() {

    private var ctlr: MediaController? = null

    @Inject
    lateinit var adapter1: ScreenAdapter

    @Inject
    lateinit var adapter2: TypeAdapter

    lateinit var gameData: String
    lateinit var gameID: String

    lateinit var gson: Gson
    lateinit var gameDataJSON: Result
    lateinit var gameDataSmall: Game

    lateinit var viewModel: SingleViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    var desc = ""

    private lateinit var wordViewModel: WordViewModel

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_game)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar!!.setDisplayHomeAsUpEnabled(true))
        val navIcon: Drawable = toolbar.getNavigationIcon()!!

        gameData = intent.extras!!.getString("GameData").toString()
        gameID = intent.extras!!.getString("id").toString()


        gson = Gson()



        if (gameData.length > 5) {


            gameDataJSON = gson?.fromJson(gameData, Result::class.java)

            viewModel = ViewModelProvider(this, providerFactory).get(SingleViewModel::class.java)
            viewModel.getInfoAboutGame(gameDataJSON.slug!!)

            subscribeObservers()

            InitViewPreview(navIcon)
        } else {

            text.visibility =View.GONE
            gameDataSmall = gson?.fromJson(gameID, Game::class.java)
            viewModel = ViewModelProvider(this, providerFactory).get(SingleViewModel::class.java)
            viewModel.getInfoAboutGame(gameDataSmall.id)

            subscribeObservers(navIcon)
        }
    }

    private fun InitViewPreview(navIcon: Drawable) {
        initRecyclerView()
        initViewPager();

        toolbar.setNavigationOnClickListener {
            finish()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            navIcon.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP);
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


        setTitle("")

        titleGameToolbar.isSelected = true;  // Set focus to the textview
        titleGameToolbar.text = gameDataJSON.name
        rateGameToolbar.text = "${gameDataJSON.rating}/${gameDataJSON.rating_top}"

        var platform: String = ""

        if (gameDataJSON.platforms != null) {
            for (i in gameDataJSON.platforms!!) {

                platform += i.platform.name + ", "
            }
        } else {
            platfroms.visibility=View.GONE
        }
        platfroms.text = platform

        text3.text = gameDataJSON.released
        titleGame.isSelected = true;
        titleGame.text = gameDataJSON.name
        rateGame.text = "${gameDataJSON.rating}"
        Picasso.get()
            .load(gameDataJSON.background_image)
            .into(back)

        if (gameDataJSON.clip != null) {
            val uri: Uri = Uri.parse(gameDataJSON.clip!!.clip)



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
            text0.visibility = View.GONE
        }
        if (gameDataJSON.short_screenshots != null)
            if (gameDataJSON.short_screenshots!!.isEmpty()) {
                text.visibility = View.GONE
            }

        //lastViewModel
//        viewModel = ViewModelProvider(this, providerFactory).get(SingleViewModel::class.java)
//        viewModel.getInfoAboutGame(gameDataJSON.slug)
//
//        subscribeObservers()


        //Add data to database with room
        wordViewModel = ViewModelProvider(this, providerFactory).get(WordViewModel::class.java)

        //        if( (gameDataJSON.id.toString())!=null){}
        // wordViewModel.viewModelScope.launch(Dispatchers.IO){

        wordViewModel.findID(gameDataJSON.id.toString()).observe(this, Observer {
            if (it.isNotEmpty()) {
                Linear.visibility = View.INVISIBLE
            }
        })

        btnNovaCompra.setOnClickListener {
            wordViewModel.insert(
                Game(
                    gameDataJSON.id.toString(),
                    gameDataJSON.name,
                    desc,
                    gameDataJSON.background_image,
                    "store"
                )
            )

            Snackbar.make(
                window.decorView.findViewById(android.R.id.content),
                "Successfully added to your library",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
        btnNovaCompraBuy.setOnClickListener {
            wordViewModel.insert(
                Game(
                    gameDataJSON.id.toString(),
                    gameDataJSON.name,
                    desc,
                    gameDataJSON.background_image,
                    "buy"
                )
            )

            Snackbar.make(
                window.decorView.findViewById(android.R.id.content),
                "Successfully added to your store",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
    }

    private fun subscribeObservers() {
        viewModel.observeSingle()!!.observe(this,
            Observer<InfoGame?> { t ->
                if (t != null) {

                    if (gameData.isEmpty()) {
                        gameDataJSON.name = t.name
                        gameDataJSON.background_image = t.background_image
                        gameDataJSON.id = t.id
                        gameDataJSON.rating_top = t.rating_top
                        gameDataJSON.rating = t.rating
                        gameDataJSON.released = t.released

                    }

                    description.text = t.description_raw
                    desc = t.description_raw


                }
            })

    }

    private fun subscribeObservers(navIcon: Drawable) {

        viewModel.observeSingle()!!.observe(this,
            Observer<InfoGame?> { t ->
                if (t != null) {

                    gameDataJSON = Result(
                        name = t.name,
                        background_image = t.background_image,
                        id = t.id,
                        rating_top = t.rating_top,
                        rating = t.rating,
                        released = t.released,
                        genres = t.genres,
                        clip = t.clip

                    )
                    gameDataJSON.name = t.name
                    gameDataJSON.background_image = t.background_image
                    gameDataJSON.id = t.id
                    gameDataJSON.rating_top = t.rating_top
                    gameDataJSON.rating = t.rating
                    gameDataJSON.released = t.released
                    gameDataJSON.genres = t.genres


                    description.text = t.description_raw
                    desc = t.description_raw

                    InitViewPreview(navIcon)
                }
            })

    }

    private fun initViewPager() {
        if (gameDataJSON.short_screenshots == null){
            viewPager.visibility =View.GONE
        }
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
        if (gameDataJSON.short_screenshots != null)
            adapter1.setPosts(gameDataJSON.short_screenshots!!)
    }

    private fun initRecyclerView() {
        gangerGame.layoutManager = LinearLayoutManager(
            this@PreviewGameActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        gangerGame.adapter = adapter2

        adapter2.setPosts(gameDataJSON.genres!!)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
