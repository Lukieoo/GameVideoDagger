package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.popularModel.ShortScreenshot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_screen.view.*
import kotlin.collections.ArrayList

class ScreenAdapter() : RecyclerView.Adapter<ScreenAdapter.ViewHolder>() {


    lateinit var items: ArrayList<ShortScreenshot>
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


    fun setPosts(items: List<ShortScreenshot>) {
        this.items = items as ArrayList<ShortScreenshot>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_screen, parent, false))

    }

    // Binds each item in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=items.get(position)
        Picasso.get()
            .load(model.image)
            .resize(850,650)
            .into( holder.photoGame)


    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to

        val photoGame = view.photoGame

    }
}

