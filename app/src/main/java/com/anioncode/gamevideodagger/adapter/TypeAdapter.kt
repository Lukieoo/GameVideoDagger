package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.ranked.Genre
import com.anioncode.gamevideodagger.model.ranked.Result
import com.anioncode.gamevideodagger.model.ranked.ShortScreenshot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ganger.view.*
import kotlinx.android.synthetic.main.item_screen.view.*
import kotlin.collections.ArrayList


class TypeAdapter() : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {


    lateinit var items: ArrayList<Genre>
   // lateinit var  itemClick:OnClickAdapterListner


    // Gets the number of games in the list

//    interface  OnClickAdapterListner{
//        fun onClick( game:ShortScreenshot)
//    }
    override fun getItemCount(): Int {
        if(::items.isInitialized){

            return items.size
        }else{
         return 0
        }

    }


    fun setPosts(items: List<Genre>) {
        this.items = items as ArrayList<Genre>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ganger, parent, false))

    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=items.get(position)
        holder.titleType.text=model.name

    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each video to

        val titleBack = view.titleBack
        val titleType = view.titleType

    }
}

