package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.popularModel.Genre
import com.anioncode.gamevideodagger.model.searchModel.Result
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_game_filter.view.*
import kotlinx.android.synthetic.main.item_screen.view.photoGame
import kotlin.collections.ArrayList


class FilterAdapter(var itemClick:OnClickAdapterListner) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {


    lateinit var items: ArrayList<Result>
    //lateinit var  itemClick:OnClickAdapterListner


    // Gets the number of games in the list

    interface  OnClickAdapterListner{
        fun onClick( result:Result)
    }
    override fun getItemCount(): Int {
        if(::items.isInitialized){

            return items.size
        }else{
         return 0
        }

    }


    fun setPosts(items: List<Result>) {
        this.items = items as ArrayList<Result>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game_filter, parent, false))

    }

    // Binds each item in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model=items.get(position)
        holder.title.text=model.name
        holder.rateGameToolbar.text="${model.rating}"

        var platform:String=""

        for (i in model.platforms) {

            platform+=i.platform.name+", "
        }

        holder.desc.text=platform


        holder.title.isSelected = true;
        holder.desc.isSelected = true;

        Picasso.get()
            .load(model.background_image)
            .resize(750,500)
                //Todo add error loading
            .into( holder.photoGame);

        holder.itemView.setOnClickListener {
            itemClick.onClick(model)
        }

    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to

        val photoGame = view.photoGame
        val title = view.title
        val rateGameToolbar = view.rateGameToolbar
        val desc = view.desc

    }
}

