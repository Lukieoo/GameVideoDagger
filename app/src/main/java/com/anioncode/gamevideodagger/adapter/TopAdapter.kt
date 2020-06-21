package com.anioncode.smogu.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.ranked.Result
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class TopAdapter(var itemClick: OnClickAdapterListner) : RecyclerView.Adapter<ViewHolder>() {


    lateinit var items: ArrayList<Result>
   // lateinit var  itemClick:OnClickAdapterListner


    // Gets the number of games in the list

    interface  OnClickAdapterListner{
        fun onClick( game:Result)
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

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false))

    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=items.get(position)
        holder.title.text=model.name
        holder.type.text="${model.rating}/${model.rating_top}"
        holder.itemView.setOnClickListener {
            itemClick.onClick(model)
        }
        Picasso.get()
            .load(model.background_image)
            .resize(690,532)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into( holder.photoGame);

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each video to
    val title = view.title
    val type = view.type
    val photoGame = view.photoGame

}