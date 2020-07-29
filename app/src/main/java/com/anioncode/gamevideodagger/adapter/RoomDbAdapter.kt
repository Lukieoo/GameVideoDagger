package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_my_game.view.*


class RoomDbAdapter(var itemClick: OnClickAdapterListner) : RecyclerView.Adapter<RoomDbAdapter.ViewHolder>() {


    lateinit var items:List<Game>
   // lateinit var  itemClick:OnClickAdapterListner


    // Gets the number of games in the list

    interface  OnClickAdapterListner{
        fun onClick( game:Game)
    }
    override fun getItemCount(): Int {
        if(::items.isInitialized){

            return items.size
        }else{
         return 0
        }

    }


    fun setPosts(items: List<Game>) {
        this.items = items as List<Game>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_game, parent, false))

    }

    // Binds each item  in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=items.get(position)
        holder.title.text=model.title
        holder.desc.text=model.desc


        Picasso.get()
            .load(model.image)
            .resize(750,500)
            .centerCrop()
            .into( holder.photoGame);

    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to
        val title   = view.titleType
        val desc   = view.desc
        val photoGame   = view.photoGame

    }
}

