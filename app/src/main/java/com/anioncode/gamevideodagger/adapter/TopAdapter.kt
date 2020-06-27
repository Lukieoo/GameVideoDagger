package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.popularModel.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlin.collections.ArrayList


class TopAdapter(var itemClick: OnClickAdapterListner) : RecyclerView.Adapter<TopAdapter.ViewHolder>() {


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

    // Binds each item  in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=items.get(position)
        holder.title.text=model.name
        holder.type.text="${model.rating}"
        holder.itemView.setOnClickListener {
            itemClick.onClick(model)
        }

        Picasso.get()
            .load(model.background_image)
//            .load("https://media.rawg.io/media/screenshots/8f2/8f244e48c17956579abc3efd0a663fd6.jpg")

            .resize(900,500)
            .centerCrop()
            .into( holder.photoGame);

    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to
        val title = view.title
        val type = view.type
        val photoGame = view.photoGame

    }
}

